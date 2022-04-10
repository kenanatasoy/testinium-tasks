package com.example.studentgrademanagement.controller;

import com.example.studentgrademanagement.dto.response.ExamGetResponse;
import com.example.studentgrademanagement.service.ExamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ExamControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService examService;

    @Test
    void assignGradeToAnExam_ReturnsOk_WhenExamIdAndGradeAreGiven() throws Exception {

        //given
        Long examId = 3L;
        Double grade = 68.0;

        ExamGetResponse examGetResponse = ExamGetResponse.builder()
                .id(examId)
                .grade(grade)
                .courseRegistryId(4L)
                .build();

        //when
        when(examService.assignGradeToAnExam(examId, grade)).thenReturn(examGetResponse);

        mockMvc.perform( put("/exams/assignGradeToAnExam?examId="
                                + examId + "&grade=" + grade )
                        .accept(MediaType.APPLICATION_JSON)
                )

        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(examId.intValue()))
                .andExpect(jsonPath("$.courseRegistryId").value(4))
                .andExpect(jsonPath("$.grade").value(grade));

        verify(examService).assignGradeToAnExam(examId, grade);

    }


    @Test
    void assignGradeToAnExam_Returns404NotFound_WhenServiceThrowsEntityNotFoundException() throws Exception {

        //given
        Long examId = 3L;
        Double grade = 68.0;

        //when
        when(examService.assignGradeToAnExam(examId, grade)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform( put("/exams/assignGradeToAnExam?examId="
                        + examId + "&grade=" + grade )
                        .accept(MediaType.APPLICATION_JSON)
                )

        //then
                .andExpect(status().isNotFound());

        verify(examService).assignGradeToAnExam(examId, grade);

    }


}