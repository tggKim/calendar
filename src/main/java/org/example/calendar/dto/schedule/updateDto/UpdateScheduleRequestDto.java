package org.example.calendar.dto.schedule.updateDto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateScheduleRequestDto {
    private String todo;
    private String username;
    private String password;
}
