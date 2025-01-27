package org.example.calendar.service.schedule;

import org.example.calendar.entity.Schedule;
import org.example.calendar.page.Paging;

import java.util.List;

public interface ScheduleService {
    Schedule saveSchedule(Schedule schedule);

    public Schedule findScheduleById(Long id);

    boolean validatePassword(Long id, String password);

    List<Schedule> findAllSchedule(Long userId, String updatedDate, String sort, Paging paging);

    void updateSchedulesTodo(Long id, String todo);

    void deleteScheduleById(Long id);

    Long getUserIdById(Long id);
}
