package org.example.calendar.service;

import org.example.calendar.entity.Schedule;

import java.util.Optional;

public interface ScheduleService {
    Schedule saveSchedule(Schedule schedule);

    public Optional<Schedule> findScheduleById(Long id);
}
