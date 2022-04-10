package com.example.studentgrademanagement.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@Builder
public class StudentAddRequest {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

}
