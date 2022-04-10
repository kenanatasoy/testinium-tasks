package com.example.studentgrademanagement.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@Builder
public class CourseRegistryGetResponse {

    private Long id;

    private String courseCode;

    private Long studentId;

    private LocalDateTime registeredAt;

    private Boolean isPassed;

    private Double averageGrade;

    private String schoolYearCode;


}
