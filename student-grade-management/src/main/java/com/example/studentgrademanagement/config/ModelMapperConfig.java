package com.example.studentgrademanagement.config;

import com.example.studentgrademanagement.dto.response.CourseRegistryGetResponse;
import com.example.studentgrademanagement.dto.response.ExamGetResponse;
import com.example.studentgrademanagement.entity.CourseRegistry;
import com.example.studentgrademanagement.entity.Exam;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    private static final Converter<CourseRegistry, CourseRegistryGetResponse>

            COURSE_REGISTRY_TO_COURSE_REGISTRY_GET_RESPONSE_CONVERTER =
                (context) -> {

            CourseRegistry courseRegistry = context.getSource();
            CourseRegistryGetResponse courseRegistryGetResponse = CourseRegistryGetResponse
                    .builder()
                    .id(courseRegistry.getId())
                    .studentId(courseRegistry.getStudent().getId())
                    .courseCode(courseRegistry.getCourse().getCode())
                    .schoolYearCode(courseRegistry.getSchoolYear().getCode())
                    .isPassed(courseRegistry.getIsPassed())
                    .registeredAt(courseRegistry.getRegisteredAt())
                    .averageGrade(courseRegistry.getAverageGrade())
                    .build();

            System.err.println("COURSE_REGISTRY_TO_COURSE_REGISTRY_GET_RESPONSE_CONVERTER: " + courseRegistryGetResponse);

            return courseRegistryGetResponse;
    };

    private static final Converter<Exam, ExamGetResponse>
            EXAM_TO_EXAM_GET_RESPONSE =
            (context) -> {

                Exam exam = context.getSource();
                ExamGetResponse examGetResponse = ExamGetResponse
                        .builder()
                        .id(exam.getId())
                        .grade(exam.getGrade())
                        .courseRegistryId(exam.getCourseRegistry().getId())
                        .build();

                System.err.println("EXAM_TO_EXAM_GET_RESPONSE: " + examGetResponse);

                return examGetResponse;
            };




    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(COURSE_REGISTRY_TO_COURSE_REGISTRY_GET_RESPONSE_CONVERTER, CourseRegistry.class, CourseRegistryGetResponse.class);
        modelMapper.addConverter(EXAM_TO_EXAM_GET_RESPONSE, Exam.class, ExamGetResponse.class);

        return modelMapper;
    }

}
