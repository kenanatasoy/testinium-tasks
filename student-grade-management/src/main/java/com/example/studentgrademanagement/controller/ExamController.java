package com.example.studentgrademanagement.controller;

import com.example.studentgrademanagement.dto.response.ExamGetResponse;
import com.example.studentgrademanagement.service.ExamService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@CrossOrigin
@RequestMapping("/exams/")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PutMapping("assignGradeToAnExam")
    public ExamGetResponse assignGradeToAnExam(@RequestParam Long examId, @RequestParam Double grade){
        return examService.assignGradeToAnExam(examId, grade);
    }



}
