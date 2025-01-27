package org.example.calendar.service.schedule;

import lombok.RequiredArgsConstructor;
import org.example.calendar.entity.Schedule;
import org.example.calendar.page.Paging;
import org.example.calendar.repository.schedule.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

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
    public List<Schedule> findAllSchedule(Long userId, String updatedDate, String sort, Paging paging) {

        // 날짜 형식 검증
        String updateDatePattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
        if(updatedDate != null && !Pattern.matches(updateDatePattern, updatedDate)){
            throw new IllegalArgumentException("올바르지 않은 날짜 형식입니다.");
        }

        // sort 형식 검증
        String sortPattern = "^(id|todo|createdDate|updatedDate)\\.(ASC|DESC|asc|desc)$";
        if(sort != null && !Pattern.matches(sortPattern, sort)){
            throw new IllegalArgumentException("정렬 형식이 올바르지 않습니다.");
        }

        // 페이징 형식 검증
        if(paging.getPage() != null && paging.getSize() != null){
            if(paging.getPage() <= 0 || paging.getSize() < 0){
                throw new IllegalArgumentException("페이징 형식이 올바르지 않습니다. size는 -1보다 크고, page는 1이상 입니다.");
            }
        }

        return scheduleRepository.findAllSchedule(userId, updatedDate, sort, paging);
    }

    @Override
    public void updateSchedulesTodo(Long id, String todo) {
        scheduleRepository.updateSchedulesTodo(id, todo);
    }

    @Override
    public boolean validatePassword(Long id, String password){
        String findPassword = scheduleRepository.getPasswordById(id).orElseThrow(() -> new NoSuchElementException("id에 해당하는 일정이 없습니다."));

        if(!findPassword.equals(password)){
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
