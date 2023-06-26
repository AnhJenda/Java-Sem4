package org.aptech.t2109e.springdemo.dto;


import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/*
    @author: Dinh Quang Anh
    Date   : 6/23/2023
    Project: spring-demo
*/
@SuperBuilder
@NoArgsConstructor
public class ProductDto {
    private long id;
    private String name;
    private long price;
    private LocalDateTime insert_time;
    private LocalDateTime update_time;
    private String createBy;
    private String updateBy;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public LocalDateTime getInsert_time() {
        return insert_time;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public String getCreateBy() {
        return createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setInsert_time(LocalDateTime insert_time) {
        this.insert_time = insert_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
