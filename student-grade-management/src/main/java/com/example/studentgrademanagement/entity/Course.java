package com.example.studentgrademanagement.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@DynamicUpdate
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@Builder
public class Course {

    @Id
    @Column(name = "code")
    private String code;

    @NotNull
    @Column(unique = true)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<CourseRegistry> courseRegistries;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        return Objects.equals(code, other.code);
    }


    @Override
    public int hashCode() {
        return Objects.hash(code);
    }


}
