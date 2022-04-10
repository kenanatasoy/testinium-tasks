package com.example.studentgrademanagement.service.business;

import com.example.studentgrademanagement.dto.response.CourseRegistryGetResponse;
import com.example.studentgrademanagement.dto.response.AllGradesResponse;
import com.example.studentgrademanagement.dto.response.StudentGradesResponse;
import com.example.studentgrademanagement.entity.*;
import com.example.studentgrademanagement.repository.CourseRegistryRepository;
import com.example.studentgrademanagement.service.CourseService;
import com.example.studentgrademanagement.service.SchoolYearService;
import com.example.studentgrademanagement.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseRegistryServiceImplUnitTests {

    @Mock
    private CourseRegistryRepository courseRegistryRepository;
    @Mock
    private StudentService studentService;
    @Mock
    private CourseService courseService;
    @Mock
    private SchoolYearService schoolYearService;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CourseRegistryServiceImpl courseRegistryServiceImpl;


    @Test
    void assignACourseToAStudent_RunsSuccessfully_WhenBothSchoolYearCodeAndCodeAreGiven() {

        //given
        String schoolYearCode = "20-21";
        String courseCode = "Che101";
        Long studentId = 2L;

        Student studentToBeAssignedCourse = Student.builder()
                                                .id(studentId)
                                                .build();
        Course courseToAssignToStudent = Course.builder()
                                                 .code(courseCode)
                                                 .build();
        SchoolYear schoolYearToAssignToCourseRegistry = SchoolYear.builder()
                .code(schoolYearCode)
                .build();

        CourseRegistry courseRegistry = CourseRegistry.builder()
                .student(studentToBeAssignedCourse)
                .course(courseToAssignToStudent)
                .schoolYear(schoolYearToAssignToCourseRegistry)
                .build();

        CourseRegistryGetResponse courseRegistryGetResponse = CourseRegistryGetResponse
                .builder()
                .studentId(studentId)
                .courseCode(courseCode)
                .schoolYearCode(schoolYearCode)
                .averageGrade(null)
                .isPassed(null)
                .build();

        //when
        when(studentService.getStudentById(studentId)).thenReturn(studentToBeAssignedCourse);
        when(courseService.getCourseByCode(courseCode)).thenReturn(courseToAssignToStudent);
        when(schoolYearService.getSchoolYearByCode(schoolYearCode)).thenReturn(schoolYearToAssignToCourseRegistry);
        when(modelMapper.map(courseRegistryRepository.save(courseRegistry), CourseRegistryGetResponse.class)).thenReturn(courseRegistryGetResponse);

        //then
        assertEquals(courseRegistryGetResponse, courseRegistryServiceImpl.assignACourseToAStudent(schoolYearCode, courseCode, studentId));

    }

    @Test
    void listAllGradesAndAverageGradeOfAStudent_ThrowsEntityNotFoundException_WhenCouldNotFindCourseRegistry() {

        //given
        String schoolYearCode = "20-21";
        String courseCode = "Che101";
        Long studentId = 2L;

        //when
        when(courseRegistryRepository.findCourseRegistryBySchoolYear_CodeAndCourse_CodeAndStudent_Id(schoolYearCode, courseCode, studentId)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class,
                () -> {
            StudentGradesResponse studentGradesResponse = courseRegistryServiceImpl.listAllGradesAndAverageGradeOfAStudent(schoolYearCode, courseCode, studentId);
        });

    }


    @Test
    void listAllGradesAndAverageGradeOfAStudent_RunsSuccessfully_WhenSchoolYearCodeAndCourseAreGiven() {

        //given
        String schoolYearCode = "20-21";
        String courseCode = "Che101";
        Long studentId = 2L;

        Long courseRegistryId = 3L;

        SchoolYear schoolYear = SchoolYear
                .builder()
                .code(schoolYearCode)
                .build();

        Course course = Course.builder()
                .code(courseCode)
                .build();

        Student student = Student.builder()
                .id(studentId)
                .firstName("Ahmet")
                .lastName("Arslan")
                .build();

        CourseRegistry courseRegistry = CourseRegistry.builder()
                .id(courseRegistryId)
                .schoolYear(schoolYear)
                .course(course)
                .student(student)
                .build();

        courseRegistry.setExams(List.of(Exam.builder().courseRegistry(courseRegistry).grade(50.0).build(), Exam.builder().grade(60.0).courseRegistry(courseRegistry).build()));

        //when
        when(courseRegistryRepository.findCourseRegistryBySchoolYear_CodeAndCourse_CodeAndStudent_Id(schoolYearCode, courseCode, studentId)).thenReturn(Optional.of(courseRegistry));

        List<Double> grades = List.of(50.0, 60.0);

        double studentGradesTotalPerCourse = 50.0 + 60.0;
        double averageGradePerCourse = studentGradesTotalPerCourse / 2;
        boolean isPassed = averageGradePerCourse > 50;

        courseRegistry.setIsPassed(isPassed);
        courseRegistry.setAverageGrade(averageGradePerCourse);

        StudentGradesResponse studentGradesResponse = StudentGradesResponse.builder()
                .schoolYearCode(schoolYearCode)
                .courseCode(courseRegistry.getCourse().getCode())
                .studentId(studentId)
                .studentFullName("Ahmet Arslan")
                .averageGrade(averageGradePerCourse)
                .isPassed(isPassed)
                .grades(grades)
                .build();

        //then
        assertEquals(studentGradesResponse, courseRegistryServiceImpl.listAllGradesAndAverageGradeOfAStudent(schoolYearCode, courseCode, studentId));

    }

    @Test
    void listAllGradesAndAverageGradeOfAllStudents_RunsSuccessfully_WhenSchoolYearCodeAndCourseCodeAreGiven() {

        //given
        String schoolYearCode = "20-21";
        String courseCode = "Che101";

        List<CourseRegistry> courseRegistries = List.of(
                CourseRegistry.builder()
                        .id(1L)
                        .schoolYear(SchoolYear.builder().code(schoolYearCode).build())
                        .course(Course.builder().code(courseCode).build())
                        .student(Student.builder().id(1L).build())
                        .exams(List.of(Exam.builder().grade(50.6).build(), Exam.builder().grade(60.2).build()))
                        .build(),
                CourseRegistry.builder()
                        .id(2L)
                        .schoolYear(SchoolYear.builder().code(schoolYearCode).build())
                        .course(Course.builder().code(courseCode).build())
                        .student(Student.builder().id(2L).build())
                        .exams(List.of(Exam.builder().grade(40.6).build(), Exam.builder().grade(50.2).build()))
                        .build(),
                CourseRegistry.builder()
                        .id(3L)
                        .schoolYear(SchoolYear.builder().code(schoolYearCode).build())
                        .course(Course.builder().code(courseCode).build())
                        .student(Student.builder().id(4L).build())
                        .exams(List.of(Exam.builder().grade(40.2).build(), Exam.builder().grade(78.3).build()))
                        .build()
        );

        //when
        when(courseRegistryRepository.findAllBySchoolYear_CodeAndCourse_Code(schoolYearCode, courseCode)).thenReturn(courseRegistries);

        List<Double> allGrades = List.of(50.6, 60.2, 40.6, 50.2, 40.2, 78.3);
        double allGradesTotal = allGrades.stream().mapToDouble(Double::doubleValue).sum();
        double averageGrade = allGradesTotal / allGrades.size();

        AllGradesResponse allGradesResponse = AllGradesResponse.builder()
                .schoolYearCode(schoolYearCode)
                .courseCode(courseCode)
                .averageGrade(averageGrade)
                .allGrades(allGrades)
                .build();

        //then
        assertEquals(allGradesResponse, courseRegistryServiceImpl.listAllGradesAndAverageGradeOfAllStudents(schoolYearCode, courseCode));

    }


}