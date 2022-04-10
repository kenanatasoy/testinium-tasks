package com.example.studentgrademanagement.repository;

import com.example.studentgrademanagement.entity.CourseRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRegistryRepository extends JpaRepository<CourseRegistry, Long> {

    Optional<CourseRegistry> findCourseRegistryBySchoolYear_CodeAndCourse_CodeAndStudent_Id(String schoolYearCode, String courseCode, Long studentId);

    List<CourseRegistry> findAllBySchoolYear_CodeAndCourse_Code(String schoolYearCode, String courseCode);

}
