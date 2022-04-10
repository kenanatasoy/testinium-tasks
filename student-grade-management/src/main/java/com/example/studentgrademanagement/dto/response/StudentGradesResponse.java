package com.example.studentgrademanagement.dto.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class StudentGradesResponse {

    private String schoolYearCode;

    private String courseCode;

    private Long studentId;

    private String studentFullName;

    private Double averageGrade;

    private Boolean isPassed;

    private List<Double> grades;

}
