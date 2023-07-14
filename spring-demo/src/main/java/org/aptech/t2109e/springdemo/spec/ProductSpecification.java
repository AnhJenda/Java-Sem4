package org.aptech.t2109e.springdemo.spec;

import org.apache.commons.lang3.StringUtils;
import org.aptech.t2109e.springdemo.dto.ProductDto;
import org.aptech.t2109e.springdemo.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 7/12/2023
    Project: spring-demo
*/
@Component
public class ProductSpecification{
    public Specification<Product> filter(final ProductDto criteria){
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
            // nếu có name truyền vào => searchByName
            if (StringUtils.isNotEmpty(criteria.getName())){
                predicates.add(criteriaBuilder.like(root.get("name"),  "%" + criteria.getName() + "%"));
            }
//            if (StringUtils.isNotEmpty(criteria.ge))
            return criteriaBuilder.and(predicates.stream()
                    .toArray(javax.persistence.criteria.Predicate[]::new));
        };
    }
}
