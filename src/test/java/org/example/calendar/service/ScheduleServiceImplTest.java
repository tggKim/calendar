package org.example.calendar.service;

import org.assertj.core.api.Assertions;
import org.example.calendar.entity.Schedule;
import org.example.calendar.service.schedule.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
class ScheduleServiceImplTest {
    @Autowired
    ScheduleService service;

//    @Test
//    @Transactional
//    void serviceSaveTest(){
//        Schedule schedule = Schedule.builder()
//                .todo("할일 테스트")
//                .username("테스트 유저")
//                .password("testPassword")
//                .build();
//
//        Schedule savedSchedule = service.saveSchedule(schedule);
//        Assertions.assertThat(savedSchedule.getTodo()).isEqualTo("할일 테스트");
//        Assertions.assertThat(savedSchedule.getUsername()).isEqualTo("테스트 유저");
//    }
//
//    @Test
//    @Transactional
//    void serviceFindByIdTest(){
//        Schedule schedule = Schedule.builder()
//                .todo("할일 테스트")
//                .username("테스트 유저")
//                .password("testPassword")
//                .build();
//
//        Schedule savedSchedule = service.saveSchedule(schedule);
//
//        Schedule findSchedule = service.findScheduleById(savedSchedule.getId());
//        Assertions.assertThat(findSchedule.getId()).isEqualTo(savedSchedule.getId());
//        Assertions.assertThat(findSchedule.getTodo()).isEqualTo("할일 테스트");
//        Assertions.assertThat(findSchedule.getUsername()).isEqualTo("테스트 유저");
//        Assertions.assertThat(findSchedule.getCreatedDate()).isEqualTo(savedSchedule.getCreatedDate());
//        Assertions.assertThat(findSchedule.getUpdatedDate()).isEqualTo(savedSchedule.getUpdatedDate());
//
//        // 없는 id 조회
//        Assertions.assertThatThrownBy(() -> service.findScheduleById(Long.MAX_VALUE)).isInstanceOf(NoSuchElementException.class);
//    }
//
//    @Test
//    void findAllScheduleTest(){
//        List<Schedule> schedules = service.findAllSchedule(null,null,null);
//        for(Schedule schedule : schedules){
//            System.out.println(schedule.getId()+" "+schedule.getTodo()+" "+schedule.getUsername()+" "+schedule.getCreatedDate()+" "+schedule.getUpdatedDate());
//        }
//    }
//
//    @Test
//    @Transactional
//    void serviceUpdateTest(){
//        Schedule schedule = Schedule.builder()
//                .todo("할일 테스트")
//                .username("테스트 유저")
//                .password("testPassword")
//                .build();
//
//        Schedule savedSchedule = service.saveSchedule(schedule);
//
//        Schedule updateSchedule = Schedule.builder()
//                .id(savedSchedule.getId())
//                .todo("할 일 업데이트")
//                .username("이름 업데이트")
//                .password(schedule.getPassword())
//                .build();
//
//        Schedule updatedSchedule = service.updateSchedulesTodoAndUsername(updateSchedule);
//
//        Assertions.assertThat(updatedSchedule.getId()).isEqualTo(savedSchedule.getId());
//        Assertions.assertThat(updatedSchedule.getTodo()).isEqualTo("할 일 업데이트");
//        Assertions.assertThat(updatedSchedule.getUsername()).isEqualTo("이름 업데이트");
//        Assertions.assertThat(updatedSchedule.getCreatedDate()).isEqualTo(savedSchedule.getCreatedDate());
//        Assertions.assertThat(updatedSchedule.getUpdatedDate()).isEqualTo(savedSchedule.getUpdatedDate());
//
//        // 틀린 비밀번호 예외 확인
//        Schedule incorrectPasswordSchedule = Schedule.builder()
//                .id(savedSchedule.getId())
//                .todo("할 일 업데이트")
//                .username("이름 업데이트")
//                .password("틀린 비밀번호")
//                .build();
//        Assertions.assertThatThrownBy(() -> service.updateSchedulesTodoAndUsername(incorrectPasswordSchedule)).isInstanceOf(IllegalArgumentException.class);
//
//        // 삭제 한 뒤 예외 확인
//        service.deleteScheduleById(updatedSchedule.getId(), schedule.getPassword());
//        Assertions.assertThatThrownBy(() -> service.updateSchedulesTodoAndUsername(updateSchedule)).isInstanceOf(NoSuchElementException.class);
//    }
//
//    @Test
//    void serviceDeleteTest(){
//        Schedule schedule = Schedule.builder()
//                .todo("할일 테스트")
//                .username("테스트 유저")
//                .password("testPassword")
//                .build();
//
//        Schedule savedSchedule = service.saveSchedule(schedule);
//
//        // 잚못된 비밀번호 테스트
//        Assertions.assertThatThrownBy(() -> service.deleteScheduleById(savedSchedule.getId(), "틀린 비밀번호")).isInstanceOf(IllegalArgumentException.class);
//
//        // 잘못된 id 테스트
//        service.deleteScheduleById(savedSchedule.getId(), schedule.getPassword());
//        Assertions.assertThatThrownBy(() -> service.deleteScheduleById(savedSchedule.getId(), schedule.getPassword())).isInstanceOf(NoSuchElementException.class);
//    }
}