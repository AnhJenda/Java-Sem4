package com.example.spring_school.repo;

import com.example.spring_school.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long>, JpaSpecificationExecutor {
    //@Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Class e WHERE e.name = :name OR e.code = :code")
    boolean existsByNameOrCode(String name, String code);
}
