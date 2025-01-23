package org.example.calendar.repository;

import org.example.calendar.dto.ScheduleRequestDto;
import org.example.calendar.dto.ScheduleResponseDto;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);

    public Optional<ScheduleResponseDto> findScheduleById(Long id);

    List<ScheduleResponseDto> findAllSchedule();

    int updateSchedulesTodoAndUsername(Long id, String todo, String username);
}
