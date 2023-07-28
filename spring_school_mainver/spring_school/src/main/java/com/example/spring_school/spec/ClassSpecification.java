package com.example.spring_school.spec;

import com.example.spring_school.dto.ClassDto;
import com.example.spring_school.entity.Class;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClassSpecification {
    public Specification<Class> filter(final ClassDto criteria){
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotEmpty(criteria.getName())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%"+criteria.getName()+"%"));
            }

            if (StringUtils.isNotEmpty(criteria.getCode())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%"+criteria.getCode()+"%"));
            }

            return criteriaBuilder.and(predicates.stream()
                    .toArray(Predicate[]::new));
        };
    }
}
