package com.example.studentgrademanagement.service.business;

import com.example.studentgrademanagement.dto.request.StudentAddRequest;
import com.example.studentgrademanagement.dto.response.StudentGetResponse;
import com.example.studentgrademanagement.entity.Student;
import com.example.studentgrademanagement.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplUnitTests {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @Test
    void addANewStudentSuccessfullyAddsStudent() {

        //given
        String firstName = "Mehmet";
        String lastName = "Günsur";
        StudentAddRequest studentAddRequest = StudentAddRequest.builder()
                                                    .firstName(firstName)
                                                    .lastName(lastName)
                                                    .build();

        Student studentToAdd = Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                            .build();

        Student addedStudent = Student.builder()
                .courseRegistries(Set.of())
                .firstName(firstName)
                .lastName(lastName)
                .build();

        StudentGetResponse studentGetResponse = StudentGetResponse.builder()
                .courseRegistryIds(Set.of())
                .firstName(firstName)
                .lastName(lastName)
                .build();

        //when
        when(modelMapper.map(studentAddRequest, Student.class)).thenReturn(studentToAdd);
        when(studentRepository.save(studentToAdd)).thenReturn(addedStudent);
        when(modelMapper.map(addedStudent, StudentGetResponse.class)).thenReturn(studentGetResponse);

        //then
        assertEquals(studentGetResponse, studentServiceImpl.addANewStudent(studentAddRequest));

    }

    @Test
    void getStudentByIdThrowsEntityNotFoundExceptionWhenStudentIsNotFound() {

        //given
        Long studentId = 7L;

        //when
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class,
            () -> {
                Student student = studentServiceImpl.getStudentById(studentId);

            });
    }

    @Test
    void getStudentById_ThrowsSuccessfullyGetsStudent_WhenStudentIdIsGiven() {

        //given
        Long studentId = 7L;

        Student student = Student.builder()
                .id(studentId)
                .build();

        //when
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        //then
        assertEquals(student, studentServiceImpl.getStudentById(studentId));

    }

}