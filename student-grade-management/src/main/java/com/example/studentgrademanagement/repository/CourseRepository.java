package com.example.studentgrademanagement.repository;

import com.example.studentgrademanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {

    Boolean existsByName(String name);

}
