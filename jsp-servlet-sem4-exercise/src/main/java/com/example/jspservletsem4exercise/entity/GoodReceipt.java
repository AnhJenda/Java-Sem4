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
//@Data
@SuperBuilder
@NoArgsConstructor
@Entity(tablename = "goodreceipt")
public class GoodReceipt {
    @Id(name = "id", dataType = SqlDataType.INTEGER)
    private int id;
    @Column(name = "goodReceiptId", dataType = SqlDataType.INTEGER)
    private int goodReceiptId;
    @Column(name = "productId", dataType = SqlDataType.INTEGER)
    private int productId;
    @Column(name = "quantity", dataType = SqlDataType.INTEGER)
    private int quantity;
    @Column(name = "receivedDate", dataType = SqlDataType.DATE)
    private Date receivedDate;
    @Column(name = "source", dataType = SqlDataType.TEXT)
    private String source;

}
