package org.example.calendar.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String todo;
    private String username;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    @Builder
    public ScheduleResponseDto(Long id, String todo, String username, LocalDate createdDate, LocalDate updatedDate){
        this.id = id;
        this.todo = todo;
        this.username = username;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
