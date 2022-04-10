package com.example.studentgrademanagement.repository;

import com.example.studentgrademanagement.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {

}
