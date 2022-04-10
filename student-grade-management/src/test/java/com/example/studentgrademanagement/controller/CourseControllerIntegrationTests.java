package com.example.studentgrademanagement.controller;

import com.example.studentgrademanagement.dto.request.CourseAddRequest;
import com.example.studentgrademanagement.dto.response.CourseGetResponse;
import com.example.studentgrademanagement.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class CourseControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CourseService courseService;


    @Test
    void shouldCreateANewCourse() throws Exception {

//        String courseCode = "Math101";
//        String courseName = "Mathematics";
//
//        CourseAddRequest courseAddRequest = CourseAddRequest.builder()
//                .code(courseCode)
//                .name(courseName)
//                .build();
//
//        CourseGetResponse expectedCourseGetResponse = CourseGetResponse.builder()
//                .code(courseCode)
//                .name(courseName)
//                .build();
//
//        when(courseService.createANewCourse(courseAddRequest)).thenReturn(expectedCourseGetResponse);
//
//        mockMvc.perform(post("/courses/createANewCourse/")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(courseAddRequest)))
//
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(expectedCourseGetResponse.getCode()))
//                .andExpect(jsonPath("$.name").value(expectedCourseGetResponse.getName()));

    }


}