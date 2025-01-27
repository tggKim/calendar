package org.example.calendar.service;

import org.assertj.core.api.Assertions;
import org.example.calendar.entity.Schedule;
import org.example.calendar.entity.User;
import org.example.calendar.repository.user.UserRepository;
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
    @Autowired
    UserRepository userRepository;

    // 저장 테스트
    @Test
    @Transactional
    void serviceSaveTest(){
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = userRepository.saveUser(user);

        Schedule schedule = Schedule.builder()
                .todo("할일 테스트")
                .userId(savedUser.getUserId())
                .password("testPassword")
                .build();

        Schedule savedSchedule = service.saveSchedule(schedule);

        Schedule findSchedule = service.findScheduleById(savedSchedule.getId());

        Assertions.assertThat(savedSchedule.getId()).isEqualTo(findSchedule.getId());
        Assertions.assertThat(savedSchedule.getTodo()).isEqualTo(findSchedule.getTodo());
        Assertions.assertThat(savedSchedule.getUserId()).isEqualTo(findSchedule.getUserId());
        Assertions.assertThat(savedSchedule.getCreatedDate()).isEqualTo(findSchedule.getCreatedDate());
        Assertions.assertThat(savedSchedule.getUpdatedDate()).isEqualTo(findSchedule.getUpdatedDate());
    }

    // 단건 조회 테스트
    @Test
    @Transactional
    void serviceFindByIdTest(){

        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = userRepository.saveUser(user);


        Schedule schedule = Schedule.builder()
                .todo("할일 테스트")
                .userId(savedUser.getUserId())
                .password("testPassword")
                .build();

        Schedule savedSchedule = service.saveSchedule(schedule);

        Schedule findSchedule = service.findScheduleById(savedSchedule.getId());

        Assertions.assertThat(findSchedule.getId()).isEqualTo(savedSchedule.getId());
        Assertions.assertThat(findSchedule.getTodo()).isEqualTo("할일 테스트");
        Assertions.assertThat(findSchedule.getUsername()).isEqualTo("테스트 유저");
        Assertions.assertThat(findSchedule.getCreatedDate()).isEqualTo(savedSchedule.getCreatedDate());
        Assertions.assertThat(findSchedule.getUpdatedDate()).isEqualTo(savedSchedule.getUpdatedDate());

    }

    // 전체 조회 테스트
    @Test
    void findAllScheduleTest(){
        List<Schedule> schedules = service.findAllSchedule(null,null,null);
        for(Schedule schedule : schedules){
            System.out.println(schedule.getId()+" "+schedule.getTodo()+" "+schedule.getUserId()+" "+schedule.getUsername()+" "+schedule.getCreatedDate()+" "+schedule.getUpdatedDate());
        }
    }

    // todo 업데이트 테스트
    @Test
    @Transactional
    void serviceUpdateTest(){
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = userRepository.saveUser(user);

        Schedule schedule = Schedule.builder()
                .todo("할일 테스트")
                .userId(savedUser.getUserId())
                .password("testPassword")
                .build();

        Schedule savedSchedule = service.saveSchedule(schedule);

        try{
            Thread.sleep(1000);
        }catch (Exception e){

        }

        service.updateSchedulesTodo(savedSchedule.getId(), "할일 업데이트");

        Schedule findSchedule = service.findScheduleById(savedSchedule.getId());

        Assertions.assertThat(findSchedule.getId()).isEqualTo(savedSchedule.getId());
        Assertions.assertThat(findSchedule.getTodo()).isEqualTo("할일 업데이트");
        Assertions.assertThat(findSchedule.getUsername()).isEqualTo("테스트 유저");
        Assertions.assertThat(findSchedule.getCreatedDate()).isEqualTo(savedSchedule.getCreatedDate());
        Assertions.assertThat(findSchedule.getUpdatedDate()).isNotEqualTo(savedSchedule.getUpdatedDate());

    }

    // 삭제 테스트
    @Test
    @Transactional
    void serviceDeleteTest(){
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = userRepository.saveUser(user);

        Schedule schedule = Schedule.builder()
                .todo("할일 테스트")
                .userId(savedUser.getUserId())
                .password("testPassword")
                .build();

        Schedule savedSchedule = service.saveSchedule(schedule);

        service.deleteScheduleById(savedSchedule.getId());

        Assertions.assertThatThrownBy(() -> service.findScheduleById(savedSchedule.getId())).isInstanceOf(NoSuchElementException.class);
    }

    // userId 가져오기 테스트
    @Test
    @Transactional
    void serviceGetUserIdTest(){
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = userRepository.saveUser(user);

        Schedule schedule = Schedule.builder()
                .todo("할일 테스트")
                .userId(savedUser.getUserId())
                .password("testPassword")
                .build();

        Schedule savedSchedule = service.saveSchedule(schedule);

        Long findUserId = service.getUserIdById(savedSchedule.getId());

        Assertions.assertThat(findUserId).isEqualTo(savedUser.getUserId());
    }

    // 비밀번호 검증 테스트
    @Test
    @Transactional
    void validatePasswordTest(){
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = userRepository.saveUser(user);

        Schedule schedule = Schedule.builder()
                .todo("할일 테스트")
                .userId(savedUser.getUserId())
                .password("testPassword")
                .build();

        Schedule savedSchedule = service.saveSchedule(schedule);

        Assertions.assertThat(service.validatePassword(savedSchedule.getId(), schedule.getPassword())).isTrue();

        Assertions.assertThatThrownBy(() -> service.validatePassword(savedSchedule.getId(), "틀린 비밀번호")).isInstanceOf(IllegalArgumentException.class);

        service.deleteScheduleById(savedSchedule.getId());

        Assertions.assertThatThrownBy(() -> service.validatePassword(savedSchedule.getId(), schedule.getPassword())).isInstanceOf(NoSuchElementException.class);
    }
}