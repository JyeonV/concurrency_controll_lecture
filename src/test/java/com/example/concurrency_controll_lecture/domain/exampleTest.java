package com.example.concurrency_controll_lecture.domain;

import com.example.concurrency_controll_lecture.domain.item.entity.Exam;
import com.example.concurrency_controll_lecture.domain.item.repository.ExamRepository;
import com.example.concurrency_controll_lecture.domain.item.service.ExamLockService;
import com.example.concurrency_controll_lecture.domain.item.service.ExamService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class exampleTest {

    @Autowired
    private ExamService examService;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ExamLockService examLockService;

    @Test
    @DisplayName("남은 시험 응시 인원이 50명일때 50번 동시 요청 시 남은 인원은 0명이다.")
    public void examRemainUsersTest() throws InterruptedException {
        //given
        Exam exam = new Exam("test", 50);
        examRepository.save(exam);

        int threadCount = 50;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        for(int i = 0; i < threadCount; i ++) {
            executor.submit(() -> {
                try {
                    examService.decreaseUsersByPessimisticLock(exam.getId());
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        //then
        Exam updatedExam = examRepository.findById(exam.getId()).orElseThrow();

        Assertions.assertEquals(0, updatedExam.getRemainUsers());
    }

    @Test
    @DisplayName("남은 시험 응시 인원이 50명일때 50번 동시 요청 시 남은 인원은 0명이다.")
    public void examRemainUsersTestBySynchronized() throws InterruptedException {
        //given
        Exam exam = new Exam("test", 50);
        examRepository.save(exam);

        int threadCount = 50;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        for(int i = 0; i < threadCount; i ++) {
            executor.submit(() -> {
                try {
                    examLockService.decreaseUsersByReentrantLock(exam.getId());
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        //then
        Exam updatedExam = examRepository.findById(exam.getId()).orElseThrow();

        Assertions.assertEquals(0, updatedExam.getRemainUsers());
    }
}
