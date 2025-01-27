package org.example.calendar.service.schedule;

import lombok.RequiredArgsConstructor;
import org.example.calendar.entity.Schedule;
import org.example.calendar.error.ErrorCode;
import org.example.calendar.exception.Exception400;
import org.example.calendar.exception.Exception401;
import org.example.calendar.exception.Exception404;
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
        return scheduleRepository.findScheduleById(id).orElseThrow(() -> new Exception404(ErrorCode.SCHEDULE_NOT_FOUND));
    }

    @Override
    public List<Schedule> findAllSchedule(Long userId, String updatedDate, String sort, Paging paging) {

        // 날짜 형식 검증
        String updateDatePattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
        if(updatedDate != null && !Pattern.matches(updateDatePattern, updatedDate)){
            throw new Exception400(ErrorCode.INVALID_DATE_FORMAT);
        }

        // sort 형식 검증
        String sortPattern = "^(id|todo|createdDate|updatedDate)\\.(ASC|DESC|asc|desc)$";
        if(sort != null && !Pattern.matches(sortPattern, sort)){
            throw new Exception400(ErrorCode.INVALID_SORT_FORMAT);
        }

        // 페이징 형식 검증
        if(paging.getPage() != null && paging.getSize() != null){
            if(paging.getPage() <= 0 || paging.getSize() < 0){
                throw new Exception400(ErrorCode.INVALID_PAGING_FORMAT);
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
        String findPassword = scheduleRepository.getPasswordById(id).orElseThrow(() -> new Exception404(ErrorCode.SCHEDULE_NOT_FOUND));

        if(!findPassword.equals(password)){
            throw new Exception401(ErrorCode.INVALID_PASSWORD);
        }

        return true;
    }

    @Override
    public Long getUserIdById(Long id) {
        return scheduleRepository.getUserIdById(id).orElseThrow(() -> new Exception404(ErrorCode.SCHEDULE_NOT_FOUND));
    }

    @Override
    public void deleteScheduleById(Long id) {
        scheduleRepository.deleteScheduleById(id);
    }

}
