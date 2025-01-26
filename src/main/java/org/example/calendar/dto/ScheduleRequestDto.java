package org.example.calendar.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.calendar.entity.Schedule;

@Getter @Setter
public class ScheduleRequestDto {
    private String todo;
    private Long userId;
    private String password;

    public Schedule toSchedule(){
        return Schedule.builder()
                .todo(this.todo)
                .userId(this.userId)
                .password(this.password)
                .build();
    }
}
