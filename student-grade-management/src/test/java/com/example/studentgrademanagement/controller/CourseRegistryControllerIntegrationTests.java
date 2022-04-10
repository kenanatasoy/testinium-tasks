package com.example.studentgrademanagement.controller;

import com.example.studentgrademanagement.dto.response.AllGradesResponse;
import com.example.studentgrademanagement.dto.response.StudentGradesResponse;
import com.example.studentgrademanagement.service.CourseRegistryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CourseRegistryControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseRegistryService courseRegistryService;

    @Test
    void listAllGradesAndAverageGradeOfAStudentReturnsOkAndSuccessfullyPerformsListing() throws Exception {

        String schoolYearCode = "20-21";
        String courseCode = "Math101";
        Long studentId = 2L;


        StudentGradesResponse studentGradesResponse = StudentGradesResponse.builder()
                .grades(List.of(60.0, 70.0))
                .courseCode(courseCode)
                .averageGrade(65.0)
                .studentFullName("Kenan Karaca")
                .studentId(studentId)
                .isPassed(true)
                .schoolYearCode(schoolYearCode)
                .build();


        when(courseRegistryService.listAllGradesAndAverageGradeOfAStudent(schoolYearCode, courseCode, studentId))
                .thenReturn(studentGradesResponse);


        mockMvc.perform(
                        get("/course_registries/listAllGradesAndAverageGradeOfAStudent?schoolYearCode=" + schoolYearCode +
                                "&courseCode=" + courseCode + "&studentId=" + studentId)
                                .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schoolYearCode",is(schoolYearCode)))
                .andExpect(jsonPath("$.courseCode",is(courseCode)))
                .andExpect(jsonPath("$.studentId",is(studentId.intValue())))
                .andExpect(jsonPath("$.studentFullName",is("Kenan Karaca")))
                .andExpect(jsonPath("$.averageGrade",is(65.0)))
                .andExpect(jsonPath("$.isPassed",is(true)))
                .andExpect(jsonPath("$.grades",is(List.of(60.0, 70.0))));


    }

    @Test
    void listAllGradesAndAverageGradeOfAllStudents() throws Exception {

        String schoolYearCode = "20-21";
        String courseCode = "Math101";

        AllGradesResponse allGradesResponse = AllGradesResponse.builder()
                .allGrades(List.of(60.0, 70.0, 80.0, 90.0))
                .averageGrade(75.0)
                .schoolYearCode(schoolYearCode)
                .courseCode(courseCode)
                .build();

        when(courseRegistryService.listAllGradesAndAverageGradeOfAllStudents(schoolYearCode, courseCode))
                .thenReturn(allGradesResponse);


        mockMvc.perform(
                        get("/course_registries/listAllGradesAndAverageGradeOfAllStudents?schoolYearCode=" + schoolYearCode +
                                "&courseCode=" + courseCode)
                                .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schoolYearCode",is(schoolYearCode)))
                .andExpect(jsonPath("$.courseCode",is(courseCode)))
                .andExpect(jsonPath("$.averageGrade",is(75.0)))
                .andExpect(jsonPath("$.allGrades", is(List.of(60.0, 70.0, 80.0, 90.0))));

    }

    @Test
    void assignACourseToAStudent() {

    }

}