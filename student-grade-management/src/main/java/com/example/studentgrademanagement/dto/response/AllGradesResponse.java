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
public class AllGradesResponse {

    private String schoolYearCode;

    private String courseCode;

    private Double averageGrade;

    private List<Double> allGrades;



}
