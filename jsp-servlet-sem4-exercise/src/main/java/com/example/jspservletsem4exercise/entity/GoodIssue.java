package com.example.jspservletsem4exercise.entity;

import com.example.jspservletsem4exercise.anotation.Column;
import com.example.jspservletsem4exercise.anotation.Entity;
import com.example.jspservletsem4exercise.anotation.Id;
import com.example.jspservletsem4exercise.constant.SqlDataType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/*
    @author: Dinh Quang Anh
    Date   : 6/16/2023
    Project: jsp-servlet-sem4-exercise
*/
@Data
@SuperBuilder
@NoArgsConstructor
@Entity(tablename = "goodissue")
public class GoodIssue {
    @Id(name = "id", dataType = SqlDataType.INTEGER)
    private int id;
    @Column(name = "issueId", dataType = SqlDataType.INTEGER)
    private int issueId;
    @Column(name = "productId", dataType = SqlDataType.INTEGER)
    private int productId;
    @Column(name = "quantity", dataType = SqlDataType.INTEGER)
    private int quantity;
    @Column(name = "issueDate", dataType = SqlDataType.DATE)
    private Date issueDate;
    @Column(name = "destination", dataType = SqlDataType.TEXT)
    private String destination;

}
