package com.example.studentgrademanagement.service.business;

import com.example.studentgrademanagement.dto.response.ExamGetResponse;
import com.example.studentgrademanagement.entity.Exam;
import com.example.studentgrademanagement.repository.ExamRepository;
import com.example.studentgrademanagement.service.ExamService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ModelMapper modelMapper;


    public ExamServiceImpl(ExamRepository examRepository, ModelMapper modelMapper) {
        this.examRepository = examRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ExamGetResponse assignGradeToAnExam(Long id, Double grade) {

        Exam examToAssignGrade = examRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Exam couldn't be found"));
        examToAssignGrade.setGrade(grade);

        return modelMapper.map(examToAssignGrade, ExamGetResponse.class);
    }



}
