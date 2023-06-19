package com.example.servlet_finalexam.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.Data;
import lombok.experimental.SuperBuilder;
/*
    @author: Dinh Quang Anh
    Date   : 6/19/2023
    Project: Servlet_FinalExam
*/
@Data
@SuperBuilder
public class DatabaseProperties {
    public static void main(String[] args){
        DatabaseProperties dbProps = new DatabaseProperties();
        System.err.println(dbProps);
    }
    private String url;
    private String username;
    private String password;
    private String driver;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public DatabaseProperties(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("application.properties");
        try {
            Properties properties = new Properties();
            properties.load(input);
            this.setUrl(properties.getProperty("database.properties.url").trim());
            this.setUsername(properties.getProperty("database.properties.username").trim());
            this.setPassword(properties.getProperty("database.properties.password").trim());
            this.setDriver(properties.getProperty("database.properties.driver-class-name").trim());
        } catch (IOException e) {
            System.err.println("Cannot read file properties \n");
            e.printStackTrace();
        }
    }
}
