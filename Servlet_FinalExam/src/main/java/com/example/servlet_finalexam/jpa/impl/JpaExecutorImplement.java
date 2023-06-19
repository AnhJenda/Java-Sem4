package com.example.servlet_finalexam.jpa.impl;

import com.example.servlet_finalexam.anotation.Column;
import com.example.servlet_finalexam.anotation.Entity;
import com.example.servlet_finalexam.anotation.Id;
import com.example.servlet_finalexam.config.DBConnection;
import com.example.servlet_finalexam.constant.SqlStatementEnum;
import com.example.servlet_finalexam.entity.Employee;
import com.example.servlet_finalexam.jpa.JpaExecutor;
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

    public void createEmployee(Employee employee) {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conn == null) {
            //todo: log
            System.err.println("connection is null");
        }

        StringBuilder columnNames = new StringBuilder();
        StringBuilder columnValues = new StringBuilder();
        List<Object> params = new ArrayList<>();

        // Lặp qua các trường của đối tượng Employee
        for (Field f : employee.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(Column.class) && !StringUtils.isEmpty(f.getAnnotation(Column.class).name())) {
                if (f.isAnnotationPresent(Id.class)) {
                    continue; // Bỏ qua trường Id
                }

                Column columnInfo = f.getAnnotation(Column.class);
                String columnName = columnInfo.name();
                f.setAccessible(true);

                try {
                    Object value = f.get(employee);
                    if (value != null) {
                        // Thêm tên cột vào danh sách tên cột
                        columnNames.append(columnName).append(",");

                        // Thêm giá trị của trường vào danh sách giá trị
                        columnValues.append("?,");

                        // Thêm giá trị của trường vào danh sách các tham số
                        params.add(value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Xóa dấu ',' cuối cùng trong danh sách tên cột và giá trị
        columnNames.deleteCharAt(columnNames.length() - 1);
        columnValues.deleteCharAt(columnValues.length() - 1);

        // Tạo câu truy vấn INSERT
        StringBuilder statement = new StringBuilder()
                .append(SqlStatementEnum.INSERT_INTO.value)
                .append(tableName)
                .append(SqlStatementEnum.OPEN_PARENTHESES.value)
                .append(columnNames)
                .append(SqlStatementEnum.CLOSE_PARENTHESES.value)
                .append(SqlStatementEnum.VALUES.value)
                .append(SqlStatementEnum.OPEN_PARENTHESES.value)
                .append(columnValues)
                .append(SqlStatementEnum.CLOSE_PARENTHESES.value);

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement.toString());

            // Đặt giá trị cho các tham số trong câu truy vấn
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }

            // Thực thi câu truy vấn INSERT
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
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