package org.example.calendar.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.calendar.entity.Schedule;

import java.time.LocalDate;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String todo;
    private String username;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.username = schedule.getUsername();
        this.createdDate = schedule.getCreatedDate();
        this.updatedDate = schedule.getUpdatedDate();
    }
}
