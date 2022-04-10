package com.example.studentgrademanagement.dto.response;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class StudentGetResponse {

    private String firstName;

    private String lastName;

    private Set<Long> courseRegistryIds;


}
