package org.aptech.t2109e.springdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*
    @author: Dinh Quang Anh
    Date   : 6/28/2023
    Project: spring-demo
*/
@Data
@NoArgsConstructor
@SuperBuilder
public class PageDto<T> {
    private int pageSize;
    private int pageNumber;
    private int totalPages;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private List<T> content;

    // Getter và setter

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}

//    private Integer totalPages;
    /* int != Integer
        Integer là object => có thể null
        int là primative => ko nhận null (kiểu nguyên thủy) - dùng sẽ tối ưu hóa bộ nhớ hơn nhưng dùng khi chắc chắn nó not null
     */
