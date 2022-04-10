package com.example.studentgrademanagement.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class CourseGetResponse {

    private String code;

    private String name;

}
