package com.example.studentgrademanagement.controller;

import com.example.studentgrademanagement.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest(
//    classes = StudentGradeManagementApplication.class,
//    webEnvironment = SpringBootTest.WebEnvironment.MOCK
//)
//@AutoConfigureMockMvc
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

//        CourseAddRequest courseAddRequest = CourseAddRequest.builder()
//                .code("Math101")
//                .name("Mathematics")
//                .build();
//
//        CourseGetResponse expectedCourseGetResponse = CourseGetResponse.builder()
//                .code("Math101")
//                .name("Mathematics")
//                .build();
//
//        when(courseService.createANewCourse(courseAddRequest)).thenReturn(expectedCourseGetResponse);
//
//        mockMvc.perform(post("/courses/createANewCourse")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(courseAddRequest)))
//
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(expectedCourseGetResponse.getCode()))
//                .andExpect(jsonPath("$.name").value(expectedCourseGetResponse.getName()));

    }

//    @Test
//    void createANewCourse() {
//    }

}