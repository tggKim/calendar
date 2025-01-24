package org.example.calendar.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class Schedule {
    private Long id;
    private String todo;
    private String username;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public  Schedule(Long id, String todo, String username, String password, LocalDateTime createdDate, LocalDateTime updatedDate){
        this.id = id;
        this.todo = todo;
        this.username = username;
        this.password = password;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
