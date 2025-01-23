package org.example.calendar.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String todo;
    private String username;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
