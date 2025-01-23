package org.example.calendar.repository;

import org.assertj.core.api.Assertions;
import org.example.calendar.dto.ScheduleRequestDto;
import org.example.calendar.dto.ScheduleResponseDto;
import org.example.calendar.entity.Schedule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScheduleRepositoryImplTest {
    @Autowired
    ScheduleRepository scheduleRepository;

    // 저장과 단건 조회 테스트
    @Test
    @Transactional
    void repositorySaveAndFindByIdTest(){
        Schedule schedule = Schedule.builder()
                .todo("할일 테스트")
                .username("테스트 유저")
                .password("testPassword")
                .build();

        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);

        Schedule findSchedule = scheduleRepository.findScheduleById(savedSchedule.getId()).get();

        Assertions.assertThat(savedSchedule.getId()).isEqualTo(findSchedule.getId());
        Assertions.assertThat(savedSchedule.getTodo()).isEqualTo(findSchedule.getTodo());
        Assertions.assertThat(savedSchedule.getUsername()).isEqualTo(findSchedule.getUsername());
        Assertions.assertThat(savedSchedule.getCreatedDate()).isEqualTo(findSchedule.getCreatedDate());
        Assertions.assertThat(savedSchedule.getUpdatedDate()).isEqualTo(findSchedule.getUpdatedDate());

    }

    // 모든 일정 조회 테스트
    @Test
    void repositoryFindAllTest(){
        List<Schedule> list = scheduleRepository.findAllSchedule();
        for(Schedule dto : list){
            System.out.println(dto.getId()+" "+dto.getTodo()+" "+dto.getUsername()+" "+dto.getCreatedDate()+" "+dto.getUpdatedDate());
        }
    }

    // 업데이트 테스트
    @Test
    @Transactional
    void repositoryUpdateTest(){
        Schedule schedule = Schedule.builder()
                .todo("할일 테스트")
                .username("테스트 유저")
                .password("testPassword")
                .build();

        Schedule savedschedule = scheduleRepository.saveSchedule(schedule);

        scheduleRepository.updateSchedulesTodoAndUsername(savedschedule.getId(), "할일 업데이트", "이름 업데이트");

        Schedule findSchedule = scheduleRepository.findScheduleById(savedschedule.getId()).get();

        Assertions.assertThat(findSchedule.getTodo()).isEqualTo("할일 업데이트");
        Assertions.assertThat(findSchedule.getUsername()).isEqualTo("이름 업데이트");
    }

}