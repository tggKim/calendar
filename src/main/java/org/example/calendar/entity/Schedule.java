package org.example.calendar.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Schedule {
    private Long id;
    private String todo;
    private String username;
    private String password;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    @Builder
    public  Schedule(Long id, String todo, String username, String password, LocalDate createdDate, LocalDate updatedDate){
        this.id = id;
        this.todo = todo;
        this.username = username;
        this.password = password;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
