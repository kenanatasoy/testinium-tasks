package com.example.studentgrademanagement.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course_registries",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = { "student_id", "course_code", "school_year_code" })
    })
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicUpdate
@Getter
@Setter
@ToString
@Builder
public class CourseRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isPassed;

    private Double averageGrade;

    @Builder.Default
    @NotNull
    private LocalDateTime registeredAt = LocalDateTime.now();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    @NotNull
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_code")
    @NotNull
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_year_code")
    @NotNull
    private SchoolYear schoolYear;

    @OneToMany(mappedBy = "courseRegistry", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Exam> exams;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CourseRegistry other = (CourseRegistry) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
