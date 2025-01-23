package org.example.calendar.repository;

import org.example.calendar.dto.ScheduleRequestDto;
import org.example.calendar.dto.ScheduleResponseDto;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);
}
