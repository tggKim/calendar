package org.example.calendar.service;

import lombok.RequiredArgsConstructor;
import org.example.calendar.entity.Schedule;
import org.example.calendar.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.saveSchedule(schedule);
    }

}
