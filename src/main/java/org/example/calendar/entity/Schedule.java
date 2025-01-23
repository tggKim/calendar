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
}
