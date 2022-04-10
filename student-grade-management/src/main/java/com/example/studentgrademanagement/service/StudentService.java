package com.example.studentgrademanagement.service;

import com.example.studentgrademanagement.dto.request.StudentAddRequest;
import com.example.studentgrademanagement.dto.response.StudentGetResponse;
import com.example.studentgrademanagement.entity.Student;

public interface StudentService {

    StudentGetResponse addANewStudent(StudentAddRequest studentAddRequest);

    Student getStudentById(Long studentId);

}

