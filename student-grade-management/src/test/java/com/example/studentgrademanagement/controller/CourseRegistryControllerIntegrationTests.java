package com.example.studentgrademanagement.controller;

import com.example.studentgrademanagement.dto.response.AllGradesResponse;
import com.example.studentgrademanagement.dto.response.CourseRegistryGetResponse;
import com.example.studentgrademanagement.dto.response.StudentGradesResponse;
import com.example.studentgrademanagement.service.CourseRegistryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CourseRegistryControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseRegistryService courseRegistryService;


    @Test
    void listAllGradesAndAverageGradeOfAStudent_Returns400BadRequest_WhenASchoolYearCodeIsNotGiven() throws Exception {

        //given
        String courseCode = "Math101";
        Long studentId = 2L;

        //when
        mockMvc.perform(
                        get("/course_registries/listAllGradesAndAverageGradeOfAStudent?courseCode=" + courseCode
                                + "&studentId=" + studentId)
                                .accept(MediaType.APPLICATION_JSON)
                )
        //then
                .andExpect(status().isBadRequest());

    }

    @Test
    void listAllGradesAndAverageGradeOfAStudent_Returns400BadRequest_WhenStudentIdIsNotGiven() throws Exception {

        //given
        String schoolYearCode = "20-21";
        String courseCode = "Math101";

        //when
        mockMvc.perform(
                        get("/course_registries/listAllGradesAndAverageGradeOfAStudent?schoolYearCode=" + schoolYearCode
                                + "&courseCode=" + courseCode)
                                .accept(MediaType.APPLICATION_JSON)
                )

        //then
                .andExpect(status().isBadRequest());
    }

    @Test
    void listAllGradesAndAverageGradeOfAStudent_Returns400BadRequest_WhenCourseCodeIsNotGiven() throws Exception {

        //given
        String schoolYearCode = "20-21";
        Long studentId = 2L;

        //when
        mockMvc.perform(
                        get("/course_registries/listAllGradesAndAverageGradeOfAStudent?schoolYearCode=" + schoolYearCode
                                + "&studentId=" + studentId)
                                .accept(MediaType.APPLICATION_JSON)
                )

        //then
                .andExpect(status().isBadRequest());
    }


    @Test
    void listAllGradesAndAverageGradeOfAStudent_Returns200OkAndSuccessfullyPerformsListing_WhenAllParametersAreGiven() throws Exception {

        //given
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

        //when
        when(courseRegistryService.listAllGradesAndAverageGradeOfAStudent(schoolYearCode, courseCode, studentId))
                .thenReturn(studentGradesResponse);


        mockMvc.perform(
                        get("/course_registries/listAllGradesAndAverageGradeOfAStudent?schoolYearCode=" + schoolYearCode +
                                "&courseCode=" + courseCode + "&studentId=" + studentId)
                                .accept(MediaType.APPLICATION_JSON)
                )

        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schoolYearCode",is(schoolYearCode)))
                .andExpect(jsonPath("$.courseCode",is(courseCode)))
                .andExpect(jsonPath("$.studentId",is(studentId.intValue())))
                .andExpect(jsonPath("$.studentFullName",is("Kenan Karaca")))
                .andExpect(jsonPath("$.averageGrade",is(65.0)))
                .andExpect(jsonPath("$.isPassed",is(true)))
                .andExpect(jsonPath("$.grades",is(List.of(60.0, 70.0))));

        verify(courseRegistryService).listAllGradesAndAverageGradeOfAStudent(schoolYearCode, courseCode, studentId);

    }



    @Test
    void listAllGradesAndAverageGradeOfAllStudents_Returns400BadRequest_WhenSchoolYearCodeIsNotGiven() throws Exception {

        //given
        String courseCode = "Math101";

        //when
        mockMvc.perform(
                        get("/course_registries/listAllGradesAndAverageGradeOfAllStudents?courseCode=" + courseCode)
                                .accept(MediaType.APPLICATION_JSON)
                )

        //then
                .andExpect(status().isBadRequest());
    }

    @Test
    void listAllGradesAndAverageGradeOfAllStudents_Returns400BadRequest_WhenCourseCodeIsNotGiven() throws Exception {

        //given
        String schoolYearCode = "20-21";

        //when
        mockMvc.perform(
                        get("/course_registries/listAllGradesAndAverageGradeOfAllStudents?schoolYearCode=" + schoolYearCode)
                                .accept(MediaType.APPLICATION_JSON)
                )

        //then
                .andExpect(status().isBadRequest());
    }



    @Test
    void listAllGradesAndAverageGradeOfAllStudents_Returns200OkAndSuccessfullyPerformsListing_WhenSchoolYearCodeAndCourseCodeAreGiven() throws Exception {

        //given
        String schoolYearCode = "20-21";
        String courseCode = "Math101";

        AllGradesResponse allGradesResponse = AllGradesResponse.builder()
                .allGrades(List.of(60.0, 70.0, 80.0, 90.0))
                .averageGrade(75.0)
                .schoolYearCode(schoolYearCode)
                .courseCode(courseCode)
                .build();

        //when
        when(courseRegistryService.listAllGradesAndAverageGradeOfAllStudents(schoolYearCode, courseCode))
                .thenReturn(allGradesResponse);


        mockMvc.perform(
                        get("/course_registries/listAllGradesAndAverageGradeOfAllStudents?schoolYearCode=" + schoolYearCode +
                                "&courseCode=" + courseCode)
                                .accept(MediaType.APPLICATION_JSON)
                )

        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schoolYearCode",is(schoolYearCode)))
                .andExpect(jsonPath("$.courseCode",is(courseCode)))
                .andExpect(jsonPath("$.averageGrade",is(75.0)))
                .andExpect(jsonPath("$.allGrades", is(List.of(60.0, 70.0, 80.0, 90.0))));

    }

    @Test
    void assignACourseToAStudent_Returns400BadRequest_WhenServiceThrowsDataIntegrityViolationException() throws Exception {

        //given
        String schoolYearCode = "20-21";
        String courseCode = "Math101";
        Long studentId = 2L;

        //when
        when(courseRegistryService.assignACourseToAStudent(schoolYearCode, courseCode, studentId))
                .thenThrow(DataIntegrityViolationException.class);


        mockMvc.perform(
                        post("/course_registries/assignACourseToAStudent?schoolYearCode=" + schoolYearCode +
                                "&courseCode=" + courseCode + "&studentId=" + studentId)
                                .accept(MediaType.APPLICATION_JSON)
                )

        //then
                .andExpect(status().isBadRequest());

        verify(courseRegistryService).assignACourseToAStudent(schoolYearCode, courseCode, studentId);

    }

    @Test
    void assignACourseToAStudent_Returns200OkAndGetsCourseRegistryGetResponse_WhenAllParametersAreGiven() throws Exception {

        //given
        String schoolYearCode = "20-21";
        String courseCode = "Math101";
        Long studentId = 2L;

        String localDateTimeNowStr = "2022-04-10T20:22:49.8475472";
        LocalDateTime localDateTimeNow = LocalDateTime.parse("2022-04-10T20:22:49.8475472");
        Double averageGrade = null;
        Boolean isPassed = null;

        CourseRegistryGetResponse expectedCourseRegistryGetResponse = CourseRegistryGetResponse.builder()
                .id(3L)
                .averageGrade(averageGrade)
                .isPassed(isPassed)
                .courseCode(courseCode)
                .schoolYearCode(schoolYearCode)
                .studentId(studentId)
                .registeredAt(localDateTimeNow)
                .build();

        //when
        when(courseRegistryService.assignACourseToAStudent(schoolYearCode, courseCode, studentId))
                .thenReturn(expectedCourseRegistryGetResponse);

        mockMvc.perform(
                post("/course_registries/assignACourseToAStudent?schoolYearCode=" + schoolYearCode +
                        "&courseCode=" + courseCode + "&studentId=" + studentId)
                        .accept(MediaType.APPLICATION_JSON))

        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(3)))
                .andExpect(jsonPath("$.courseCode", is(courseCode)))
                .andExpect(jsonPath("$.studentId", is(studentId.intValue())))
                .andExpect(jsonPath("$.registeredAt", is(localDateTimeNowStr)))
                .andExpect(jsonPath("$.isPassed", is(isPassed)))
                .andExpect(jsonPath("$.averageGrade", is(averageGrade)))
                .andExpect(jsonPath("$.schoolYearCode", is(schoolYearCode)));

        verify(courseRegistryService).assignACourseToAStudent(schoolYearCode, courseCode, studentId);

    }



}
