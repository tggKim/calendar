package org.example.calendar.service;

import org.example.calendar.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    Schedule saveSchedule(Schedule schedule);

    public Optional<Schedule> findScheduleById(Long id);

    List<Schedule> findAllSchedule();

    int updateSchedulesTodoAndUsername(Schedule schedule);
}
