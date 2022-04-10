package com.example.studentgrademanagement.service.business;

import com.example.studentgrademanagement.entity.SchoolYear;
import com.example.studentgrademanagement.repository.SchoolYearRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SchoolYearServiceImplTest {

    @Mock
    private SchoolYearRepository schoolYearRepository;

    @InjectMocks
    private SchoolYearServiceImpl schoolYearServiceImpl;

    @Test
    void getSchoolYearByCodeThrowsEntityNotFoundExceptionWhenCouldNotFindTheSchoolYear() {

        //given
        String schoolYearCode = "19-20";

        //when
        when(schoolYearRepository.findById(schoolYearCode)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class,
            () -> {
                SchoolYear schoolYear = schoolYearServiceImpl.getSchoolYearByCode(schoolYearCode);
        });
    }


    @Test
    void getSchoolYearByCodeGetsSchoolYearSuccessfully() {

        //given
        String schoolYearCode = "19-20";

        SchoolYear schoolYear = SchoolYear.builder()
                                    .code(schoolYearCode)
                                    .name("2019-2020")
                                    .isActive(false)
                                    .build();

        //when
        when(schoolYearRepository.findById(schoolYearCode)).thenReturn(Optional.of(schoolYear));

        //then
        assertEquals(schoolYear, schoolYearServiceImpl.getSchoolYearByCode(schoolYearCode));

    }





}