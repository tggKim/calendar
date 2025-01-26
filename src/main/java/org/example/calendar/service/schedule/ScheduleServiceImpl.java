package org.example.calendar.service.schedule;

import lombok.RequiredArgsConstructor;
import org.example.calendar.entity.Schedule;
import org.example.calendar.repository.schedule.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public Schedule findScheduleById(Long id) {
        return scheduleRepository.findScheduleById(id).orElseThrow(() -> new NoSuchElementException("id에 해당하는 일정이 없습니다."));
    }

    @Override
    public List<Schedule> findAllSchedule(Long userId, String updatedDate, String sort) {
        return scheduleRepository.findAllSchedule(userId, updatedDate, sort);
    }

    @Override
    public void updateSchedulesTodo(Schedule schedule) {
        scheduleRepository.updateSchedulesTodo(schedule.getId(), schedule.getTodo());
    }

    @Override
    public boolean validatePassword(Schedule schedule){
        String findPassword = scheduleRepository.getPasswordById(schedule.getId()).orElseThrow(() -> new NoSuchElementException("id에 해당하는 일정이 없습니다."));

        if(!findPassword.equals(schedule.getPassword())){
            throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
        }

        return true;
    }

    @Override
    public Long getUserIdById(Long id) {
        return scheduleRepository.getUserIdById(id).orElseThrow(() -> new NoSuchElementException("id에 해당하는 일정이 없습니다."));
    }

    @Override
    public void deleteScheduleById(Long id) {
        scheduleRepository.deleteScheduleById(id);
    }

}
