package org.example.calendar.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class Schedule {
    private Long id;
    private Long userId;
    private String username;
    private String todo;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public  Schedule(Long id, Long userId, String username, String todo, String password, LocalDateTime createdDate, LocalDateTime updatedDate){
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.todo = todo;
        this.password = password;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
