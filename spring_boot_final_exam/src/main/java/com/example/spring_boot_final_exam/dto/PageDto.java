package com.example.spring_boot_final_exam.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*
    @author: Dinh Quang Anh
    Date   : 7/5/2023
    Project: spring_boot_final_exam
*/
@Data
@NoArgsConstructor
@SuperBuilder
public class PageDto<T>{
    private Integer pageSize;
    private Integer pageNumber;

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
