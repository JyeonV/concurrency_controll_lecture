package com.example.concurrency_controll_lecture.domain.item.service;

import com.example.concurrency_controll_lecture.domain.item.entity.Exam;
import com.example.concurrency_controll_lecture.domain.item.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @Transactional
    public void decreaseUsers(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시험입니다."));

        exam.decreaseRemainUsers();
    }

    @Transactional
    public void decreaseUsers2(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시험입니다."));

        exam.decreaseRemainUsers();
    }

    @Transactional
    public void decreaseUsersByPessimisticLock(Long examId) {
        Exam exam = examRepository.findByIdForUpdate(examId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시험입니다."));

        exam.decreaseRemainUsers();
    }
}
