package com.example.studentgrademanagement.service.business;

import com.example.studentgrademanagement.dto.response.ExamGetResponse;
import com.example.studentgrademanagement.entity.CourseRegistry;
import com.example.studentgrademanagement.entity.Exam;
import com.example.studentgrademanagement.repository.ExamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamServiceImplUnitTests {

    @Mock
    private ExamRepository examRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ExamServiceImpl examServiceImpl;

    @Test
    void assignGradeToAnExam_ThrowsEntityNotFoundException_WhenCouldNotFindTheExamToAssignGradeTo() {

        //given
        Long id = 54645654L;
        Double grade = 65.5;

        //when
        when(examRepository.findById(id)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class,
                () -> {
                    examServiceImpl.assignGradeToAnExam(id, grade);
                }
        );

    }


    @Test
    void assignGradeToAnExam_SuccessfullyAssignsGradeToTheExam_WhenExamIdAndExamGradeAreGiven() {

        //given
        Long examId = 54645654L;
        Double examGrade = 65.5;
        Long courseRegistryId = 76856756L;

        Exam examToAssignGrade = Exam.builder()
                                    .id(examId)
                                    .courseRegistry(CourseRegistry.builder().id(courseRegistryId).build())
                                    .build();

        examToAssignGrade.setGrade(examGrade);

        ExamGetResponse examGetResponse = ExamGetResponse.builder()
                                                .id(examId)
                                                .grade(examGrade)
                                                .courseRegistryId(courseRegistryId)
                                                .build();


        //when
        when(examRepository.findById(examId)).thenReturn(Optional.of(examToAssignGrade));
        when(modelMapper.map(examToAssignGrade, ExamGetResponse.class)).thenReturn(examGetResponse);


        //then
        assertEquals(examGetResponse, examServiceImpl.assignGradeToAnExam(examId, examGrade));

    }




}