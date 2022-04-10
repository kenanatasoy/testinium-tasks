package com.example.studentgrademanagement.controller;

import com.example.studentgrademanagement.dto.response.CourseRegistryGetResponse;
import com.example.studentgrademanagement.dto.response.AllGradesResponse;
import com.example.studentgrademanagement.dto.response.StudentGradesResponse;
import com.example.studentgrademanagement.service.CourseRegistryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@CrossOrigin
@RequestMapping("/courseregistries/")
public class CourseRegistryController {

    private final CourseRegistryService courseRegistryService;

    public CourseRegistryController(CourseRegistryService courseRegistryService) {
        this.courseRegistryService = courseRegistryService;
    }

    @GetMapping("listAllGradesAndAverageGradeOfAStudent")// #demand1
    public StudentGradesResponse listAllGradesAndAverageGradeOfAStudent(@RequestParam String schoolYearCode,
                                                                        @RequestParam String courseCode,
                                                                        @RequestParam Long studentId) {
        return courseRegistryService.listAllGradesAndAverageGradeOfAStudent(schoolYearCode, courseCode, studentId);
    }

    @GetMapping("listAllGradesAndAverageGradeOfAllStudents")// #demand2
    public AllGradesResponse listAllGradesAndAverageGradeOfAllStudents(@RequestParam String schoolYearCode,
                                                                    @RequestParam String courseCode) {
        return courseRegistryService.listAllGradesAndAverageGradeOfAllStudents(schoolYearCode, courseCode);
    }


    @PostMapping("assignACourseToAStudent")// #demand4
    public CourseRegistryGetResponse assignACourseToAStudent(@RequestParam String schoolYearCode,
                                                             @RequestParam String courseCode,
                                                             @RequestParam Long studentId){
        return courseRegistryService.assignACourseToAStudent(schoolYearCode, courseCode, studentId);
    }



}
