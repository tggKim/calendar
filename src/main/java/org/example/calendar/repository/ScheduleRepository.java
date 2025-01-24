package org.example.calendar.repository;

import org.example.calendar.dto.ScheduleRequestDto;
import org.example.calendar.dto.ScheduleResponseDto;
import org.example.calendar.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule saveSchedule(Schedule schedule);

    public Optional<Schedule> findScheduleById(Long id);

    List<Schedule> findAllSchedule(String username, String updatedDate, String sort);

    int updateSchedulesTodoAndUsername(Long id, String todo, String username);

    int deleteScheduleById(Long id);

    Optional<String> getUserPasswordById(Long id);
}
