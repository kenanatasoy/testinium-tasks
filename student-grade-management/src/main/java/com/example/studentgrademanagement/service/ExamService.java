package com.example.studentgrademanagement.service;

import com.example.studentgrademanagement.dto.response.ExamGetResponse;

public interface ExamService {

    ExamGetResponse assignGradeToAnExam(Long examId, Double grade);

}
