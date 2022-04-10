package com.example.studentgrademanagement.service;

import com.example.studentgrademanagement.dto.request.CourseAddRequest;
import com.example.studentgrademanagement.dto.response.CourseGetResponse;
import com.example.studentgrademanagement.entity.Course;

public interface CourseService {

    CourseGetResponse createANewCourse(CourseAddRequest courseAddRequest);

    Course getCourseByCode(String code);

}
