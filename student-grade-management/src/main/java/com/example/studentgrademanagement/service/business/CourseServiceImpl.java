package com.example.studentgrademanagement.service.business;

import com.example.studentgrademanagement.dto.request.CourseAddRequest;
import com.example.studentgrademanagement.dto.response.CourseGetResponse;
import com.example.studentgrademanagement.entity.Course;
import com.example.studentgrademanagement.repository.CourseRepository;
import com.example.studentgrademanagement.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseGetResponse createANewCourse(CourseAddRequest courseAddRequest) {

        Course courseToAdd = modelMapper.map(courseAddRequest, Course.class);

        if(courseRepository.existsById(courseToAdd.getCode())) {
            throw new EntityExistsException("Course with the same code already exists");
        }

        if(courseRepository.existsByName(courseToAdd.getName())) {
            throw new EntityExistsException("Course with the same name already exits");
        }

        return modelMapper.map(courseRepository.save(courseToAdd), CourseGetResponse.class);
    }

    @Override
    public Course getCourseByCode(String code) {
        return courseRepository.findById(code).orElseThrow(() -> new EntityNotFoundException("Course couldn't be found"));
    }


}
