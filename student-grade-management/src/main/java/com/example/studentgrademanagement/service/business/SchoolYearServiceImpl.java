package com.example.studentgrademanagement.service.business;

import com.example.studentgrademanagement.entity.SchoolYear;
import com.example.studentgrademanagement.repository.SchoolYearRepository;
import com.example.studentgrademanagement.service.SchoolYearService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class SchoolYearServiceImpl implements SchoolYearService {

    private final SchoolYearRepository schoolYearRepository;

    public SchoolYearServiceImpl(SchoolYearRepository schoolYearRepository) {
        this.schoolYearRepository = schoolYearRepository;
    }

    @Override
    public SchoolYear getSchoolYearByCode(String code) {
        return schoolYearRepository.findById(code).orElseThrow(() -> new EntityNotFoundException("School year couldn't be found"));
    }


}
