package com.example.servlet_finalexam.jpa.impl;

import com.example.servlet_finalexam.anotation.Column;
import com.example.servlet_finalexam.anotation.Entity;
import com.example.servlet_finalexam.anotation.Id;
import com.example.servlet_finalexam.config.DBConnection;
import com.example.servlet_finalexam.constant.SqlStatementEnum;
import com.example.servlet_finalexam.entity.Employee;
import com.example.servlet_finalexam.jpa.JpaExecutor;
import com.mysql.cj.api.jdbc.Statement;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/19/2023
    Project: Servlet_FinalExam
*/
public class JpaExecutorImplement <T> implements JpaExecutor<T> {
    private Class<T> clazz;
    private String className;
    private String tableName;
    private Id Id;


    public JpaExecutorImplement(Class<T> clazz){
        this.clazz = clazz;
        this.className = clazz.getSimpleName();
        this.tableName = (clazz.getAnnotation(Entity.class) != null) ? clazz.getAnnotation(Entity.class).tablename()
                : this.className.toLowerCase();
        this.Id = clazz.getAnnotation(Id.class);
    }


    @Override
    public List<T> findall(){
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conn == null){
            //todo: log
            System.err.println("connection is null");
        }
        StringBuilder statement = new StringBuilder()
                .append(SqlStatementEnum.SELECT_ASTERISK.value)
                .append(SqlStatementEnum.SPACE.value)
                .append(SqlStatementEnum.FROM.value)
                .append(SqlStatementEnum.SPACE.value)
                .append(tableName);
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement.toString());
            ResultSet rs = preparedStatement.executeQuery();
            return entityParser(rs);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public T getById(int id) {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(conn == null) {
            // todo: log
            System.err.println("Connection is null" + conn);
        } else {
            System.err.println(conn);
        }
        String idColumn = null;
        // get column name of id
        for(Field f : clazz.getDeclaredFields()){
            if (f.isAnnotationPresent(Id.class)){
                // id is existed
//                idColumn = f.getAnnotation(Id.class).name();

                idColumn = (StringUtils.isEmpty(f.getAnnotation(Id.class).name())) ? f.getName() : f.getAnnotation(Id.class).name().trim();
            }
        }
        StringBuilder statement = new StringBuilder().append(SqlStatementEnum.SELECT_ASTERISK.value)
                .append(SqlStatementEnum.SPACE.value).append(SqlStatementEnum.FROM).append(SqlStatementEnum.SPACE.value).append(tableName).append(SqlStatementEnum.SPACE.value).append(SqlStatementEnum.WHERE).append(SqlStatementEnum.SPACE.value).append(idColumn).append(SqlStatementEnum.SPACE.value).append(SqlStatementEnum.EQUAL).append(SqlStatementEnum.QUEST);
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement.toString());
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            List<T> results = entityParser(rs);
            if (results != null && results.size() > 0){
                return results.get(0);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public void deleteRecord(int id) {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(conn == null) {
            // todo: log
            System.err.println("Connection is null" + conn);
        } else {
            System.err.println(conn);
        }
        String idColumn = null;
        // get column name of id
        for(Field f : clazz.getDeclaredFields()){
            if (f.isAnnotationPresent(Id.class)){
                // id is existed
//                idColumn = f.getAnnotation(Id.class).name();

                idColumn = (StringUtils.isEmpty(f.getAnnotation(Id.class).name())) ? f.getName() : f.getAnnotation(Id.class).name().trim();
            }
        }
        StringBuilder statement = new StringBuilder()
                .append(SqlStatementEnum.DELETE.value)
                .append(SqlStatementEnum.SPACE.value).append(SqlStatementEnum.FROM)
                .append(SqlStatementEnum.SPACE.value).append(tableName)
                .append(SqlStatementEnum.SPACE.value)
                .append(SqlStatementEnum.WHERE)
                .append(SqlStatementEnum.SPACE.value)
                .append(idColumn).append(SqlStatementEnum.SPACE.value)
                .append(SqlStatementEnum.EQUAL)
                .append(SqlStatementEnum.QUEST);
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement.toString());
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete record failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createNewRecord(T object) {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conn == null) {
            // todo: log
            System.err.println("connection is null");
        }

        StringBuilder columnNames = new StringBuilder(); // khởi tạo danh sách tên cột
        StringBuilder columnValues = new StringBuilder(); // khởi tạo danh sách giá trị cột
        List<Object> params = new ArrayList<>();  // danh sách các tham số cho câu lệnh insert

        // Lặp qua các trường của bảng
        for (Field f : object.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(Column.class) && !StringUtils.isEmpty(f.getAnnotation(Column.class).name())) {
                if (f.isAnnotationPresent(Id.class)) {
                    continue; // Vì Id set là auto_increment nên cần bỏ qua trường này
                }

                Column columnInfo = f.getAnnotation(Column.class);
                String columnName = columnInfo.name();  // lấy ra tên cột
                f.setAccessible(true);  // cho phép truy cập vào các trường hoặc phương thức private trong reflection

                try {
                    Object value = f.get(object);
                    if (value != null) {
                        // Thêm tên cột vào list cột của bảng
                        columnNames.append(columnName).append(",");

                        // Thêm ký tự '?' đại diện cho giá trị insert của các cột
                        columnValues.append("?,");

                        // thêm giá trị insert vào
                        params.add(value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Xóa đi dấu ',' ở sau cột cuối cùng
        columnNames.deleteCharAt(columnNames.length() - 1);
        // Xóa đi dấu ',' ở sau parameter cuối cùng
        columnValues.deleteCharAt(columnValues.length() - 1);

        // Tạo câu lệnh insert : Insert in to table 'tên bảng' (cột 1, cột 2, ...) values (?, ?, ...)
        StringBuilder statement = new StringBuilder()
                .append(SqlStatementEnum.INSERT_INTO.value)
                .append(SqlStatementEnum.SPACE.value)
                .append(tableName)
                .append(SqlStatementEnum.OPEN_PARENTHESES.value)
                .append(columnNames)
                .append(SqlStatementEnum.CLOSE_PARENTHESES.value)
                .append(SqlStatementEnum.SPACE.value)
                .append(SqlStatementEnum.VALUES.value)
                .append(SqlStatementEnum.OPEN_PARENTHESES.value)
                .append(columnValues)
                .append(SqlStatementEnum.CLOSE_PARENTHESES.value);

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement.toString());

            // Sử dụng vòng lặp để set giá trị cho các cột
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }

            // execute câu lệnh insert
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("creating " + tableName + " failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void updateRecord(int id, T object){
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conn == null) {
            // todo: log
            System.err.println("connection is null");
        }
        StringBuilder updateQuery = new StringBuilder(); // khởi tạo câu lệnh UPDATE
        List<Object> params = new ArrayList<>(); // danh sách các tham số cho câu lệnh update

        // Lặp qua các trường của bảng
        for (Field f : object.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(Column.class) && !StringUtils.isEmpty(f.getAnnotation(Column.class).name())) {
                if (f.isAnnotationPresent(Id.class)) {
                    continue; // Vì Id set là auto_increment nên cần bỏ qua trường này
                }

                Column columnInfo = f.getAnnotation(Column.class);
                String columnName = columnInfo.name();  // lấy ra tên cột
                f.setAccessible(true);  // cho phép truy cập vào các trường hoặc phương thức private trong reflection

                try {
                    Object value = f.get(object);
                    if (value != null) {
                        // Thêm tên cột và giá trị vào câu lệnh UPDATE
                        updateQuery.append(columnName).append(" = ?,");
                        params.add(value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Xóa dấu ',' ở cuối câu lệnh UPDATE
        updateQuery.deleteCharAt(updateQuery.length() - 1);

        // Tạo câu lệnh UPDATE: UPDATE 'tên bảng' SET cột 1 = ?, cột 2 = ?, ... WHERE id = ?
        StringBuilder statement = new StringBuilder()
                .append(SqlStatementEnum.UPDATE.value)
                .append(SqlStatementEnum.SPACE.value)
                .append(tableName)
                .append(SqlStatementEnum.SPACE.value)
                .append(SqlStatementEnum.SET.value)
                .append(SqlStatementEnum.SPACE.value)
                .append(updateQuery.toString())
                .append(SqlStatementEnum.SPACE.value)
                .append(SqlStatementEnum.WHERE.value)
                .append(SqlStatementEnum.SPACE.value)
                .append("id = ?");
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement.toString());

            // Sử dụng vòng lặp để set giá trị cho các cột
            int paramIndex = 1;
            for (Object param : params) {
                preparedStatement.setObject(paramIndex, param);
                paramIndex++;
            }
            // Đặt giá trị cho tham số id
            Field idField = object.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            Object idValue = idField.get(object);
            preparedStatement.setObject(params.size() + 1, idValue);

            // execute câu lệnh insert
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("update " + tableName + " failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    @Override
    public List<T> entityParser(ResultSet rs){
        List<T> entities = new ArrayList<>();
        try {
            while (rs.next()){
                T entity = clazz.getDeclaredConstructor().newInstance();
                for (Field f : entity.getClass().getDeclaredFields()){
                    String columnName = f.getName();
                    if (f.isAnnotationPresent(Column.class) && !StringUtils.isEmpty(f.getAnnotation(Column.class).name())){
                        Column columnInfo = f.getAnnotation(Column.class);
                        columnName = columnInfo.name();
                        f.setAccessible(true);
                        switch (columnInfo.dataType()){
                            case INTEGER: f.set(entity, rs.getInt(columnName));
                                break;
                            case TEXT: f.set(entity, rs.getString(columnName));
                                break;
                            case DOUBLE: f.set(entity, rs.getDouble(columnName));
                                break;
                            case BIG_INTEGER: f.set(entity, rs.getInt(columnName));
                                break;
                            case DATE: f.set(entity, rs.getDate(columnName));
                                break;
                            case FLOAT:
                                f.set(entity, rs.getFloat(columnName));
                                break;
                            case SMALL_INTEGER:
                                f.set(entity, rs.getInt(columnName));
                                break;
                            // todo : lam chua xong ve lam not
                            // fixme: lam sai day lam lai di
                        }
                    }
                }
                entities.add(entity);
            }
        }
        catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            throw new  RuntimeException();
        }
        return entities;
    }
}

