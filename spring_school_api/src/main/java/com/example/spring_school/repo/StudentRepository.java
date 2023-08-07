package com.example.spring_school.repo;

import com.example.spring_school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
    @author: Dinh Quang Anh
    Date   : 8/7/2023
    Project: spring_school_api
*/
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor {
    @Query(value = "select * from student_table s join student_class sc on s.id = sc.student_id join class_table c on sc.class_id = c.id where sc.class_id=:id", nativeQuery = true)
    List<Student> getStudentsByClasses(@Param("id") Long id);
}
