package com.example.studentgrademanagement.controller;

import com.example.studentgrademanagement.dto.request.CourseAddRequest;
import com.example.studentgrademanagement.dto.response.CourseGetResponse;
import com.example.studentgrademanagement.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseControllerUnitTests {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @Test
    void createANewCourseSuccessfullyCreatesNewCourse() {

        //given
        CourseAddRequest courseAddRequest = CourseAddRequest.builder()
                .code("Math101")
                .name("Mathematics")
                .build();

        CourseGetResponse expectedCourseGetResponse = CourseGetResponse.builder()
                .code("Math101")
                .name("Mathematics")
                .build();

        //when
        when(courseService.createANewCourse(courseAddRequest)).thenReturn(expectedCourseGetResponse);

        //then
        assertEquals(expectedCourseGetResponse, courseController.createANewCourse(courseAddRequest));

    }



}