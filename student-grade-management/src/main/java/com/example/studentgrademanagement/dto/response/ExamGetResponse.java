package com.example.studentgrademanagement.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class ExamGetResponse {

    private Long id;

    private Long courseRegistryId;

    private Double grade;

}
