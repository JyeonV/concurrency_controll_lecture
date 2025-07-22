package com.example.concurrency_controll_lecture.domain.item.service;

import com.example.concurrency_controll_lecture.domain.item.entity.Exam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamLockService {

    private final ExamService examService;

    public synchronized void decreaseUsersBySynchronized(Long examId) {
        examService.decreaseUsers2(examId);
    }
}
