package com.example.studentgrademanagement.service;

import com.example.studentgrademanagement.dto.response.CourseRegistryGetResponse;
import com.example.studentgrademanagement.dto.response.AllGradesResponse;
import com.example.studentgrademanagement.dto.response.StudentGradesResponse;

public interface CourseRegistryService {

    CourseRegistryGetResponse assignACourseToAStudent(String schoolYearCode, String courseCode, Long studentId);

    StudentGradesResponse listAllGradesAndAverageGradeOfAStudent(String schoolYearCode, String courseCode, Long studentId);

    AllGradesResponse listAllGradesAndAverageGradeOfAllStudents(String schoolYearCode, String courseCode);


}
