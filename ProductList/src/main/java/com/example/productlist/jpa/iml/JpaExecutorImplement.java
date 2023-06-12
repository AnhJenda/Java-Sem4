package com.example.productlist.jpa.iml;

import com.example.productlist.anotation.Column;
import com.example.productlist.anotation.Entity;
import com.example.productlist.anotation.Id;
import com.example.productlist.config.DBConnection;
import com.example.productlist.constant.SqlStatementEnum;
import com.example.productlist.jpa.JpaExecutor;
import com.example.productlist.jpa.exception.NoTableColumnFoundException;
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
    Date   : 6/2/2023
    Project: ProductList
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
    public T getByField(String columnName, String criValue) {
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
        String criteriaColumnName = null;
        // get column name of id
        for(Field f : clazz.getDeclaredFields()){
            if (f.isAnnotationPresent(Column.class)){
                if(f.getAnnotation(Column.class).name().trim().equals(columnName.trim())){
                    criteriaColumnName = columnName;
                    break;
                }
            }
        }
        if(StringUtils.isEmpty(criteriaColumnName)){
            throw new NoTableColumnFoundException("Column name not found: " + columnName);
        }
        StringBuilder statement = new StringBuilder().append(SqlStatementEnum.SELECT_ASTERISK.value)
                .append(SqlStatementEnum.SPACE.value).append(SqlStatementEnum.FROM).append(SqlStatementEnum.SPACE.value).append(tableName).append(SqlStatementEnum.SPACE.value).append(SqlStatementEnum.WHERE).append(SqlStatementEnum.SPACE.value).append(criteriaColumnName).append(SqlStatementEnum.SPACE.value).append(SqlStatementEnum.EQUAL).append(criValue);
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement.toString());
            preparedStatement.setString(1, criValue);
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

    public List<T> findProduct(int id){
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
                .append(tableName)
                .append(SqlStatementEnum.WHERE.value)
                .append(Id)
                .append(SqlStatementEnum.EQUAL.value)
                .append(id);
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
