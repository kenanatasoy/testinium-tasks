package com.example.studentgrademanagement.controller;

import com.example.studentgrademanagement.dto.request.CourseAddRequest;
import com.example.studentgrademanagement.dto.response.CourseGetResponse;
import com.example.studentgrademanagement.service.CourseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@RequestMapping("/courses/")
@CrossOrigin
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("createANewCourse")// #demand3
    public CourseGetResponse createANewCourse(@RequestBody @Validated CourseAddRequest courseAddRequest) {
        return courseService.createANewCourse(courseAddRequest);
    }



}
