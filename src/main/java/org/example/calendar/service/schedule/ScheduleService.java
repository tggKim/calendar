package org.example.calendar.service.schedule;

import org.example.calendar.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule saveSchedule(Schedule schedule);

    public Schedule findScheduleById(Long id);

    boolean validatePassword(Long id, String password);

    List<Schedule> findAllSchedule(Long userId, String updatedDate, String sort);

    void updateSchedulesTodo(Schedule schedule);

    void deleteScheduleById(Long id);

    Long getUserIdById(Long id);
}
