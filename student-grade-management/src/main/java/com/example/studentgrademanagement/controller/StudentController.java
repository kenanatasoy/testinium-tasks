package com.example.studentgrademanagement.controller;

import com.example.studentgrademanagement.dto.request.StudentAddRequest;
import com.example.studentgrademanagement.dto.response.StudentGetResponse;
import com.example.studentgrademanagement.entity.Student;
import com.example.studentgrademanagement.service.StudentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@CrossOrigin
@RequestMapping("/students/")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("addANewStudent")
    public StudentGetResponse addANewStudent(@RequestBody @Validated StudentAddRequest studentAddRequest){
        return studentService.addANewStudent(studentAddRequest);
    }


}
