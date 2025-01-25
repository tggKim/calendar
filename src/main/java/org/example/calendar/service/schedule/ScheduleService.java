package org.example.calendar.service.schedule;

import org.example.calendar.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule saveSchedule(Schedule schedule);

    public Schedule findScheduleById(Long id);

    List<Schedule> findAllSchedule(String username, String updatedDate, String sort);

    Schedule updateSchedulesTodo(Schedule schedule);

    void deleteScheduleById(Long id, String password);
}
