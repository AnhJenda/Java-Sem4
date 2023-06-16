package com.example.day4.impl;

import com.example.day4.JpaExecutor;
import com.example.day4.anotations.Column;
import com.example.day4.anotations.Entity;
import com.example.day4.anotations.Id;
import com.example.day4.config.DBConnection;
import com.example.day4.constant.SqlStatementEnum;
import com.example.day4.utils.SelectQueryBuilder;
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
    Date   : 6/14/2023
    Project: day4
*/


public class JpaExecutorImpl<T> implements JpaExecutor<T> {
    private Class<T> clazz;
    private String className;
    private String tableName;

    public JpaExecutorImpl(Class<T> clazz){
        this.clazz = clazz;
        this.className = clazz.getSimpleName();
        this.tableName = (clazz.getAnnotation(Entity.class) != null) ? clazz.getAnnotation(Entity.class).tablename() :
                this.className.toLowerCase();
    }

    public List<T> entityParser(ResultSet rs){
        List<T> entitys = new ArrayList<>();
        try {
            while(rs.next()){
                T entity = clazz.getDeclaredConstructor().newInstance();
                for(Field f : entity.getClass().getDeclaredFields()){
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
                            case BIG_INTEGER: f.set(entity, rs.getInt(columnName));
                                break;
                            case SMALL_INTEGER: f.set(entity, rs.getInt(columnName));
                                break;
                            case DATE: f.set(entity, rs.getDate(columnName));
                                break;
                            case FLOAT: f.set(entity, rs.getFloat(columnName));
                                break;
                            case DOUBLE: f.set(entity, rs.getInt(columnName));
                                break;
                            // todo : Làm tiếp tục với các kiểu dữ liệu còn lại
                            // fixme: Fix nốt đê
                        }
                    }
                }
                entitys.add(entity);
            }
        }
        catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException();
        }

        return entitys;
    }
    public List<T> findall(){
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

        StringBuilder statement = new StringBuilder().append(SqlStatementEnum.SELECT_ASTERISK.value)
                .append(SqlStatementEnum.SPACE.value).append(SqlStatementEnum.FROM).append(SqlStatementEnum.SPACE.value).append(tableName);

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement.toString());
            ResultSet rs = preparedStatement.executeQuery();
//            String title = rs.getString("title");
            return entityParser(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T getById(int id) {
        SelectQueryBuilder select = new SelectQueryBuilder(tableName);
        String idColumnName = null;
        for (Field f: clazz.getDeclaredFields()){
            if (f.isAnnotationPresent(Id.class)){
                idColumnName = f.getAnnotation(Id.class).name();
            }
        }
        select.andClause(idColumnName + " = " + id);
        System.err.println(select.getQuery());
        return null;
    }
}
