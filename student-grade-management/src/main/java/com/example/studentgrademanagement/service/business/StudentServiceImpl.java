package com.example.studentgrademanagement.service.business;

import com.example.studentgrademanagement.dto.request.StudentAddRequest;
import com.example.studentgrademanagement.dto.response.StudentGetResponse;
import com.example.studentgrademanagement.entity.Student;
import com.example.studentgrademanagement.repository.StudentRepository;
import com.example.studentgrademanagement.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public StudentGetResponse addANewStudent(StudentAddRequest studentAddRequest) {

        Student studentToAdd = modelMapper.map(studentAddRequest, Student.class);

        Student addedStudent = studentRepository.save(studentToAdd);

        return modelMapper.map(addedStudent, StudentGetResponse.class);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow( () -> new EntityNotFoundException("Student couldn't be found") );
    }


}

