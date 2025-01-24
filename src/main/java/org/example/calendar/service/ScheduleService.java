package org.example.calendar.service;

import org.example.calendar.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    Schedule saveSchedule(Schedule schedule);

    public Schedule findScheduleById(Long id);

    List<Schedule> findAllSchedule();

    Schedule updateSchedulesTodoAndUsername(Schedule schedule);

    void deleteScheduleById(Long id, String password);
}
