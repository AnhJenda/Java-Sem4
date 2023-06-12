package com.example.productlist.jpa.iml;

import com.example.productlist.anotation.Column;
import com.example.productlist.anotation.Entity;
import com.example.productlist.config.DBConnection;
import com.example.productlist.constant.SqlStatementEnum;
import com.example.productlist.jpa.JpaExecutor;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
    @author: Dinh Quang Anh
    Date   : 6/2/2023
    Project: ProductList
*/
public class JpaExecutorImplement <T> implements JpaExecutor <T> {
    private Class<T> clazz;
    private String className;
    private String tableName;


    public JpaExecutorImplement(Class<T> clazz){
        this.clazz = clazz;
        this.className = clazz.getSimpleName();
        this.tableName = (clazz.getAnnotation(Entity.class) != null) ? clazz.getAnnotation(Entity.class).tablename()
                : this.className.toLowerCase();
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
