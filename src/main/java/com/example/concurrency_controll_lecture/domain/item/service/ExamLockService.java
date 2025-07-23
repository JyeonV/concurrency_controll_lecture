package com.example.concurrency_controll_lecture.domain.item.service;

import com.example.concurrency_controll_lecture.domain.item.entity.Exam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class ExamLockService {

    private final ExamService examService;

    private final ReentrantLock reentrantLock = new ReentrantLock();

    public synchronized void decreaseUsersBySynchronized(Long examId) {
        examService.decreaseUsers2(examId);
    }

    public void decreaseUsersByReentrantLock(Long examId) {
        try {
            if(reentrantLock.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    examService.decreaseUsers2(examId);
                } finally {
                    reentrantLock.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
