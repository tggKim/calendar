package org.example.calendar.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.calendar.entity.Schedule;

@Getter @Setter
public class ScheduleRequestDto {
    private String todo;
    private String username;
    private String password;

    public Schedule toSchedule(){
        return Schedule.builder()
                .todo(this.todo)
                .username(this.todo)
                .password(this.todo)
                .build();
    }
}
