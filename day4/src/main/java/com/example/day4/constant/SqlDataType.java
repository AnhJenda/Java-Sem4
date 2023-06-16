package com.example.day4.constant;

/*
    @author: Dinh Quang Anh
    Date   : 6/2/2023
    Project: ProductList
*/
public enum SqlDataType {
    INTEGER("INT"),
    SMALL_INTEGER("SMALLINT"),
    BIG_INTEGER("BIGINT"),
    FLOAT("FLOAT"),
    DOUBLE("DOUBLE"),
    DATE("DATE"),
    DATETIME("DATETIME"),
    TIME_STAMP("TIMESTAMP"),
    TEXT("TEXT");
    public String value ;
    private SqlDataType(String value) {
        this.value = value;
    }
}
