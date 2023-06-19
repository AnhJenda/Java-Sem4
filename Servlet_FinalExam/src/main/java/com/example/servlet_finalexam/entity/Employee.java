package com.example.servlet_finalexam.entity;

import com.example.servlet_finalexam.anotation.Column;
import com.example.servlet_finalexam.anotation.Entity;
import com.example.servlet_finalexam.anotation.Id;
import com.example.servlet_finalexam.constant.SqlDataType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/*
    @author: Dinh Quang Anh
    Date   : 6/19/2023
    Project: Servlet_FinalExam
*/
@Data
@SuperBuilder
@NoArgsConstructor
@Entity(tablename = "employee")
public class Employee {
    @Id(name = "id", dataType = SqlDataType.INTEGER)
    private int id;
    @Column(name = "fullname", dataType = SqlDataType.TEXT)
    private String fullname;
    @Column(name = "birthday", dataType = SqlDataType.DATE)
    private Date birthday;
    @Column(name = "address", dataType = SqlDataType.TEXT)
    private String address;
    @Column(name = "position", dataType = SqlDataType.TEXT)
    private String position;
    @Column(name = "department", dataType = SqlDataType.TEXT)
    private String department;
}
