package org.example.calendar.repository.schedule;

import org.example.calendar.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule saveSchedule(Schedule schedule);

    public Optional<Schedule> findScheduleById(Long id);

    List<Schedule> findAllSchedule(String username, String updatedDate, String sort);

    int updateSchedulesTodo(Long id, String todo);

    int deleteScheduleById(Long id);

    Optional<String> getUserPasswordById(Long id);
}
