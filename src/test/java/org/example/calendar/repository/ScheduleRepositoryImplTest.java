package org.example.calendar.repository;

import org.assertj.core.api.Assertions;
import org.example.calendar.entity.Schedule;
import org.example.calendar.repository.schedule.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        List<Schedule> list = scheduleRepository.findAllSchedule(null,null,null);
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

    // 삭제 테스트
    @Test
    @Transactional
    void repositoryDeleteTest(){
        Schedule schedule = Schedule.builder()
                .todo("삭제 테스트")
                .username("테스트 유저")
                .password("testPassword")
                .build();

        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);

        int number = scheduleRepository.deleteScheduleById(savedSchedule.getId());
        Assertions.assertThat(number).isEqualTo(1);

        number = scheduleRepository.deleteScheduleById(savedSchedule.getId());
        Assertions.assertThat(number).isEqualTo(0);
    }

    // id로 비밀번호 찾기 테스트
    @Test
    @Transactional
    void repositoryFindPasswordTest(){
        Schedule schedule = Schedule.builder()
                .todo("삭제 테스트")
                .username("테스트 유저")
                .password("testPassword")
                .build();

        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);

        String findPassword = scheduleRepository.getUserPasswordById(savedSchedule.getId()).get();
        Assertions.assertThat(findPassword).isEqualTo(schedule.getPassword());

        scheduleRepository.deleteScheduleById(savedSchedule.getId());
        Optional<String> findPassword02 = scheduleRepository.getUserPasswordById(savedSchedule.getId());
        Assertions.assertThat(findPassword02.isEmpty()).isTrue();
    }
}