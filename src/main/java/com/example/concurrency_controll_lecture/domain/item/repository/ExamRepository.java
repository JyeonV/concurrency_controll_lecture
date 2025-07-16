package com.example.concurrency_controll_lecture.domain.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.concurrency_controll_lecture.domain.item.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
}
