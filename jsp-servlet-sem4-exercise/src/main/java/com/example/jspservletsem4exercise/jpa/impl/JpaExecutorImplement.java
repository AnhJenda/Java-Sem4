package com.example.jspservletsem4exercise.jpa.impl;

import com.example.jspservletsem4exercise.anotation.Column;
import com.example.jspservletsem4exercise.anotation.Entity;
import com.example.jspservletsem4exercise.anotation.Id;
import com.example.jspservletsem4exercise.config.DBConnection;
import com.example.jspservletsem4exercise.constant.SqlStatementEnum;
import com.example.jspservletsem4exercise.jpa.JpaExecutor;
import com.example.jspservletsem4exercise.jpa.exception.NoTableColumnFoundException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/16/2023
    Project: jsp-servlet-sem4-exercise
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

                idColumn = (StringUtils.isEmpty(f.getAnnotation(Id.class).name())) ? f.getName() : f.getAnnotation(Id.class).name().trim();
            }
        }
        StringBuilder statement = new StringBuilder().append(SqlStatementEnum.SELECT_ASTERISK.value)
                .append(SqlStatementEnum.SPACE.value)
                .append(SqlStatementEnum.FROM)
                .append(SqlStatementEnum.SPACE.value)
                .append(tableName)
                .append(SqlStatementEnum.SPACE.value)
                .append(SqlStatementEnum.WHERE)
                .append(SqlStatementEnum.SPACE.value)
                .append(idColumn)
                .append(SqlStatementEnum.SPACE.value)
                .append(SqlStatementEnum.EQUAL)
                .append(SqlStatementEnum.QUEST);
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement.toString());
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            List<T> results = entityParser(rs);
            if (results != null && results.size() > 0){
                return results.get(0);
            }
            return null;
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public T getByField(String columnName, String criValue) {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (conn == null) {
            // todo: log
            System.err.println("Connection is null" + conn);
        } else {
            System.err.println(conn);
        }
        String criteriaColumnName = null;
        // get column name of id
        for (Field f : clazz.getDeclaredFields()) {
            if (f.isAnnotationPresent(Column.class)) {
                if (f.getAnnotation(Column.class).name().trim().equalsIgnoreCase(columnName.trim())) {
                    criteriaColumnName = columnName;
                    break;
                }
            }
        }
        if (StringUtils.isEmpty(criteriaColumnName)) {
            throw new NoTableColumnFoundException("Column name not found: " + columnName);
        }
        StringBuilder statement = new StringBuilder().append(SqlStatementEnum.SELECT_ASTERISK.value)
                .append(SqlStatementEnum.SPACE.value)
                .append(SqlStatementEnum.FROM)
                .append(SqlStatementEnum.SPACE.value)
                .append(tableName)
                .append(SqlStatementEnum.SPACE.value)
                .append(SqlStatementEnum.WHERE)
                .append(SqlStatementEnum.SPACE.value)
                .append(criteriaColumnName)
                .append(SqlStatementEnum.SPACE.value)
                .append(SqlStatementEnum.EQUAL.value)
                .append(SqlStatementEnum.SPACE.value);

        // Kiểm tra kiểu dữ liệu của criValue
        if (StringUtils.isNumeric(criValue)) {
            // Giá trị là số, không cần đặt trong dấu ngoặc
            statement.append(criValue);
        } else if (isDateValue(criValue)){
            statement.append(SqlStatementEnum.QUEST.value);
        }
        else {
            // Giá trị là chuỗi, đặt trong cặp dấu ngoặc đơn
            statement.append(SqlStatementEnum.APOSTROPHE.value)
                    .append(criValue)
                    .append(SqlStatementEnum.APOSTROPHE.value);
        }

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement.toString());

            if (isDateValue(criValue)) {
                preparedStatement.setDate(1, parseDateValue(criValue));
            }
            ResultSet rs = preparedStatement.executeQuery();
            List<T> results = entityParser(rs);
            if (results != null && results.size() > 0) {
                return results.get(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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

    private boolean isDateValue(String value) {
        // Kiểm tra giá trị có phải là date hay không
        // Bạn có thể sử dụng các phương thức của thư viện DateTime để kiểm tra định dạng date
        // Ví dụ: sử dụng SimpleDateFormat để kiểm tra định dạng dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            dateFormat.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private java.sql.Date parseDateValue(String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // Chuyển đổi từ String sang java.util.Date
            java.util.Date utilDate = dateFormat.parse(value);

            // Chuyển đổi từ java.util.Date sang java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            return sqlDate;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date value: " + value);
        }
    }
}
