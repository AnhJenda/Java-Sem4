package com.example.spring_school_client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
public class PageDto<T> {
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
    /* int () != Integer
    Integer: là 1 object -> có thể null
    int: là primative kiểu dữ liệu nguyên thủy (dùng khi chắc chắn là not null)
    */
}
