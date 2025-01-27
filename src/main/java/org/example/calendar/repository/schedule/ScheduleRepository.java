package org.example.calendar.repository.schedule;

import org.example.calendar.entity.Schedule;
import org.example.calendar.page.Paging;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule saveSchedule(Schedule schedule);

    public Optional<Schedule> findScheduleById(Long id);

    List<Schedule> findAllSchedule(Long userId, String updatedDate, String sort, Paging Paging);

    int updateSchedulesTodo(Long id, String todo);

    int deleteScheduleById(Long id);

    Optional<String> getPasswordById(Long id);

    Optional<Long> getUserIdById(Long id);
}
