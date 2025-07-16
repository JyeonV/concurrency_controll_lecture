package com.example.concurrency_controll_lecture.domain.item.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int remainUsers;

    public Exam(String name, int remainUsers) {
        this.name = name;
        this.remainUsers = remainUsers;
    }

    public void decreaseRemainUsers() {
        this.remainUsers --;
    }
}
