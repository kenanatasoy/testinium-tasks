package com.example.studentgrademanagement.service.business;

import com.example.studentgrademanagement.dto.request.CourseAddRequest;
import com.example.studentgrademanagement.dto.response.CourseGetResponse;
import com.example.studentgrademanagement.entity.Course;
import com.example.studentgrademanagement.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CourseServiceImplUnitTests {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CourseServiceImpl courseServiceImpl;


    @Test
    void createANewCourse_ThrowsEntityExistsException_WhenCourseExistsByCode() {

        //given
        String courseCode = "Math45";
        String courseName = "Mathematics";

        CourseAddRequest courseAddRequest = CourseAddRequest
                                                .builder()
                                                .code(courseCode)
                                                .name(courseName)
                                                .build();

        Course courseToAdd = Course
                                .builder()
                                .code(courseCode)
                                .name(courseName)
                                .build();

        //when
        when(modelMapper.map(courseAddRequest, Course.class)).thenReturn(courseToAdd);
        when(courseRepository.existsById(courseToAdd.getCode())).thenReturn(true);

        //then
        assertThrows(EntityExistsException.class,
            () -> {
                CourseGetResponse courseGetResponse = courseServiceImpl.createANewCourse(courseAddRequest);
            }
        );

    }


    @Test
    void createANewCourse_ThrowsEntityExistsException_WhenCourseExistsByName() {

        //given
        String courseCode = "Math45";
        String courseName = "Mathematics";

        CourseAddRequest courseAddRequest = CourseAddRequest
                                                .builder()
                                                .code(courseCode)
                                                .name(courseName)
                                                .build();
        Course courseToAdd = Course
                                .builder()
                                .code(courseCode)
                                .name(courseName)
                                .build();

        //when
        when(modelMapper.map(courseAddRequest, Course.class)).thenReturn(courseToAdd);
        when(courseRepository.existsByName(courseToAdd.getName())).thenReturn(true);

        //then
        assertThrows(EntityExistsException.class,
                () -> {
                    CourseGetResponse courseGetResponse = courseServiceImpl.createANewCourse(courseAddRequest);
                }
        );

    }


    @Test
    void createANewCourse_CreatesANewCourseSuccessFully_WhenCourseCodeAndCourseNameAreGiven() {

        //given
        String courseCode = "Math45";
        String courseName = "Mathematics";

        CourseAddRequest courseAddRequest = CourseAddRequest
                .builder()
                .code(courseCode)
                .name(courseName)
                .build();
        Course courseToAdd = Course
                .builder()
                .code(courseCode)
                .name(courseName)
                .build();

        CourseGetResponse courseGetResponse = CourseGetResponse
                                                .builder()
                                                .code(courseCode)
                                                .name(courseName)
                                                .build();


        //when
        when(modelMapper.map(courseAddRequest, Course.class)).thenReturn(courseToAdd);
        when(courseRepository.existsById(courseToAdd.getCode())).thenReturn(false);
        when(courseRepository.existsByName(courseToAdd.getName())).thenReturn(false);
        when(modelMapper.map(courseRepository.save(courseToAdd), CourseGetResponse.class)).thenReturn(courseGetResponse);


        //then
        assertEquals(courseGetResponse, courseServiceImpl.createANewCourse(courseAddRequest));

    }




    @Test
    void getCourseByCode_ThrowsEntityNotFoundException_WhenCouldNotFindCourse(){

        //given
        String courseCode = "Math45";


        //when
        when(courseRepository.findById(courseCode)).thenReturn(Optional.empty());


        //then
        assertThrows(EntityNotFoundException.class,
                () -> {
                    Course course = courseServiceImpl.getCourseByCode(courseCode);
                }
        );

    }


    @Test
    void getCourseByCode_SuccessfullyGetsCourse_WhenCourseCodeAndCourseNameAreGiven(){

        //given
        String courseCode = "Math45";
        String courseName = "Mathematics";

        Course course = Course
                            .builder()
                            .code(courseCode)
                            .name(courseName)
                            .build();

        //when
        when(courseRepository.findById(courseCode)).thenReturn(Optional.of(course));


        //then
        assertEquals(course, courseServiceImpl.getCourseByCode(courseCode));

    }



}