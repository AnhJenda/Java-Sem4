package com.example.spring.spec;

import com.example.spring.dto.ProductDto;
import com.example.spring.entity.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSpecification{

    //reflection câu truy vấn
    public Specification<Product> filter(final ProductDto criteria) {
        // root là gôc - Select * from product_table
        // criteriaQuery
        // criteriaBuilder - builder phần sau (điều kiện)
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // nếu có name truyền vào -> search by name
            if (StringUtils.isNotEmpty(criteria.getName())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%"+criteria.getName().toUpperCase()+"%"));
                 // lấy ra các trường muốn so sánh
            }

            if (criteria.getMaxPrice() > 0) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), criteria.getMaxPrice()));
            }

            if (criteria.getMinPrice() > 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), criteria.getMinPrice()));
            }

            return criteriaBuilder.and(predicates.stream()
                    .toArray(Predicate[]::new));
        };
    }
}
