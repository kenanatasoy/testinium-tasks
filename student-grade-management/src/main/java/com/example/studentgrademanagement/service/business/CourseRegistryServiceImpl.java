package com.example.studentgrademanagement.service.business;

import com.example.studentgrademanagement.dto.response.CourseRegistryGetResponse;
import com.example.studentgrademanagement.dto.response.AllGradesResponse;
import com.example.studentgrademanagement.dto.response.StudentGradesResponse;
import com.example.studentgrademanagement.entity.*;
import com.example.studentgrademanagement.repository.CourseRegistryRepository;
import com.example.studentgrademanagement.service.CourseRegistryService;
import com.example.studentgrademanagement.service.CourseService;
import com.example.studentgrademanagement.service.SchoolYearService;
import com.example.studentgrademanagement.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CourseRegistryServiceImpl implements CourseRegistryService {

    private final CourseRegistryRepository courseRegistryRepository;
    private final StudentService studentService;
    private final CourseService courseService;
    private final SchoolYearService schoolYearService;
    private final ModelMapper modelMapper;


    public CourseRegistryServiceImpl(CourseRegistryRepository courseRegistryRepository, StudentService studentService, CourseService courseService, SchoolYearService schoolYearService, ModelMapper modelMapper) {
        this.courseRegistryRepository = courseRegistryRepository;
        this.studentService = studentService;
        this.courseService = courseService;
        this.schoolYearService = schoolYearService;
        this.modelMapper = modelMapper;
    }


    @Override
    public CourseRegistryGetResponse assignACourseToAStudent(String schoolYearCode, String courseCode, Long studentId) {

        Student studentToBeAssignedCourse = studentService.getStudentById(studentId);
        Course courseToAssignToStudent = courseService.getCourseByCode(courseCode);
        SchoolYear schoolYearToAssignToCourseRegistry = schoolYearService.getSchoolYearByCode(schoolYearCode);

        CourseRegistry courseRegistry = CourseRegistry.builder()
                                            .student(studentToBeAssignedCourse)
                                            .course(courseToAssignToStudent)
                                            .schoolYear(schoolYearToAssignToCourseRegistry)
                                            .build();

        CourseRegistryGetResponse courseRegistryGetResponse = modelMapper.map(courseRegistryRepository.save(courseRegistry), CourseRegistryGetResponse.class);

        assignTwoExamsToTheCourseRegistry(courseRegistry);

        return courseRegistryGetResponse;

    }

    private void assignTwoExamsToTheCourseRegistry(CourseRegistry courseRegistry) {
        List<Exam> exams = List.of(Exam.builder().courseRegistry(courseRegistry).build(),
                                      Exam.builder().courseRegistry(courseRegistry).build());
        courseRegistry.setExams(exams);
    }


    @Override
    public StudentGradesResponse listAllGradesAndAverageGradeOfAStudent(String schoolYearCode, String courseCode, Long studentId) {

        CourseRegistry courseRegistry = courseRegistryRepository.findCourseRegistryBySchoolYear_CodeAndCourse_CodeAndStudent_Id(schoolYearCode, courseCode, studentId).orElseThrow(() -> new EntityNotFoundException("Course registry couldn't be found"));
        List<Double> grades = courseRegistry.getExams().stream()
                                    .map(Exam::getGrade)
                                    .filter(Objects::nonNull)
                                    .toList();

        double studentGradesTotalPerCourse = grades.stream().mapToDouble(Double::doubleValue).sum();
        double averageGradePerCourse = studentGradesTotalPerCourse / 2;
        boolean isPassed = averageGradePerCourse > 50;

        courseRegistry.setIsPassed(isPassed);
        courseRegistry.setAverageGrade(averageGradePerCourse);

        return StudentGradesResponse.builder()
                .averageGrade(averageGradePerCourse)
                .schoolYearCode(schoolYearCode)
                .isPassed(isPassed)
                .studentId(studentId)
                .grades(grades)
                .studentFullName(courseRegistry.getStudent().getFirstName() + " " + courseRegistry.getStudent().getLastName())
                .courseCode(courseRegistry.getCourse().getCode())
                .build();
    }


    @Override
    public AllGradesResponse listAllGradesAndAverageGradeOfAllStudents(String schoolYearCode, String courseCode) {

        List<CourseRegistry> courseRegistries = courseRegistryRepository.findAllBySchoolYear_CodeAndCourse_Code(schoolYearCode, courseCode);
        List<Double> allGrades = courseRegistries.stream()
                                    .map(CourseRegistry::getExams)
                                    .flatMap(Collection::stream)
                                    .map(Exam::getGrade)
                                    .filter(Objects::nonNull)
                                    .toList();

        double allGradesTotal = allGrades.stream().mapToDouble(Double::doubleValue).sum();
        double averageGrade = allGradesTotal / allGrades.size();

        return AllGradesResponse.builder()
                        .schoolYearCode(schoolYearCode)
                        .courseCode(courseCode)
                        .averageGrade(averageGrade)
                        .allGrades(allGrades)
                        .build();
    }

}
