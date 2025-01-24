package org.example.calendar.service;

import lombok.RequiredArgsConstructor;
import org.example.calendar.entity.Schedule;
import org.example.calendar.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        return scheduleRepository.findScheduleById(id);
    }

    @Override
    public List<Schedule> findAllSchedule() {
        return List.of();
    }

    @Override
    public int updateSchedulesTodoAndUsername(Schedule schedule) {

        String findPassword = scheduleRepository.getUserPasswordById(schedule.getId()).orElseThrow(() -> new NoSuchElementException("id에 해당하는 일정이 없습니다."));

        if(findPassword.equals(schedule.getPassword())){
            scheduleRepository.updateSchedulesTodoAndUsername(schedule.getId(), schedule.getTodo(), schedule.getUsername());
        }
        else{
            throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
        }

        return 0;
    }


}
