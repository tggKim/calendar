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
    public List<Schedule> findAllSchedule(String username, String updatedDate, String sort) {
        return scheduleRepository.findAllSchedule(username,updatedDate,sort);
    }

    @Override
    public Schedule updateSchedulesTodoAndUsername(Schedule schedule) {
        String findPassword = scheduleRepository.getUserPasswordById(schedule.getId()).orElseThrow(() -> new NoSuchElementException("id에 해당하는 일정이 없습니다."));

        if(findPassword.equals(schedule.getPassword())){
            scheduleRepository.updateSchedulesTodoAndUsername(schedule.getId(), schedule.getTodo(), schedule.getUsername());
        }
        else{
            throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
        }

        // 무조건 id에 해당하는 일정이  존재하므로 예외 던질 필요 없음
        return scheduleRepository.findScheduleById(schedule.getId()).get();
    }

    @Override
    public void deleteScheduleById(Long id, String password) {
        String findPassword = scheduleRepository.getUserPasswordById(id).orElseThrow(() -> new NoSuchElementException("id에 해당하는 일정이 없습니다."));

        if(findPassword.equals(password)){
            scheduleRepository.deleteScheduleById(id);
        }
        else{
            throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
        }
    }
}
