package com.example.studentgrademanagement.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class CourseAddRequest {

    @NotNull
    private String code;

    @NotNull
    private String name;

}
