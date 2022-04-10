package com.example.studentgrademanagement.repository;

import com.example.studentgrademanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {


}
