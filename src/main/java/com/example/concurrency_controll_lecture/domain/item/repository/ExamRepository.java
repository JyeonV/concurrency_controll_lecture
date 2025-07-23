package com.example.concurrency_controll_lecture.domain.item.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.concurrency_controll_lecture.domain.item.entity.Exam;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM Exam e WHERE e.id = :id")
    Optional<Exam> findByIdForUpdate(@Param("id") Long id);
}
