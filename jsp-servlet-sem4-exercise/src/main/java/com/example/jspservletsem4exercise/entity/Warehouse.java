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
@Entity(tablename = "warehouse")
public class Warehouse {
    @Id(name = "id", dataType = SqlDataType.INTEGER)
    private int id;
    @Column(name = "productId", dataType = SqlDataType.INTEGER)
    private int productId;
    @Column(name = "receivedDate", dataType = SqlDataType.DATETIME)
    private Date receivedDate;
    @Column(name = "quantity", dataType = SqlDataType.INTEGER)
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getRecievedDate() {
        return receivedDate;
    }

    public void setRecievedDate(Date recievedDate) {
        this.receivedDate = recievedDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
