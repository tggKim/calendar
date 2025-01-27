package org.example.calendar.repository.schedule;

import org.assertj.core.api.Assertions;
import org.example.calendar.entity.Schedule;
import org.example.calendar.entity.User;
import org.example.calendar.page.Paging;
import org.example.calendar.repository.user.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    // 저장과 단건 조회 테스트
    @Test
    @Transactional
    void repositorySaveAndFindByIdTest(){

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

        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);

        Schedule findSchedule = scheduleRepository.findScheduleById(savedSchedule.getId()).get();

        Assertions.assertThat(savedSchedule.getId()).isEqualTo(findSchedule.getId());
        Assertions.assertThat(savedSchedule.getTodo()).isEqualTo(findSchedule.getTodo());
        Assertions.assertThat(savedSchedule.getUserId()).isEqualTo(findSchedule.getUserId());
        Assertions.assertThat(savedSchedule.getCreatedDate()).isEqualTo(findSchedule.getCreatedDate());
        Assertions.assertThat(savedSchedule.getUpdatedDate()).isEqualTo(findSchedule.getUpdatedDate());

    }

    // 모든 일정 조회 테스트
    @Test
    void repositoryFindAllTest(){
        List<Schedule> list = scheduleRepository.findAllSchedule(null,null,null,new Paging());
        for(Schedule dto : list){
            System.out.println(dto.getId()+" "+dto.getTodo()+" "+dto.getUserId()+" "+dto.getUsername()+" "+dto.getCreatedDate()+" "+dto.getUpdatedDate());
        }
    }

    // todo 업데이트 테스트
    @Test
    @Transactional
    void repositoryUpdateTest(){

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

        Schedule savedschedule = scheduleRepository.saveSchedule(schedule);

        scheduleRepository.updateSchedulesTodo(savedschedule.getId(), "할일 업데이트");

        Schedule findSchedule = scheduleRepository.findScheduleById(savedschedule.getId()).get();

        Assertions.assertThat(findSchedule.getTodo()).isEqualTo("할일 업데이트");
    }

    // 삭제 테스트
    @Test
    @Transactional
    void repositoryDeleteTest(){
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = userRepository.saveUser(user);

        Schedule schedule = Schedule.builder()
                .todo("삭제 테스트")
                .userId(savedUser.getUserId())
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
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = userRepository.saveUser(user);

        Schedule schedule = Schedule.builder()
                .todo("비밀번호 테스트")
                .userId(savedUser.getUserId())
                .password("testPassword")
                .build();

        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);

        String findPassword = scheduleRepository.getPasswordById(savedSchedule.getId()).get();
        Assertions.assertThat(findPassword).isEqualTo(schedule.getPassword());

        scheduleRepository.deleteScheduleById(savedSchedule.getId());
        Optional<String> findPassword02 = scheduleRepository.getPasswordById(savedSchedule.getId());
        Assertions.assertThat(findPassword02.isEmpty()).isTrue();
    }

    // id로 userId 찾기 테스트
    @Test
    @Transactional
    void repositoryFindUserIdTest(){
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = userRepository.saveUser(user);

        Schedule schedule = Schedule.builder()
                .todo("userId찾기 테스트")
                .userId(savedUser.getUserId())
                .password("testPassword")
                .build();

        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);

        Schedule findSchedule = scheduleRepository.findScheduleById(savedSchedule.getId()).get();
        Assertions.assertThat(findSchedule.getUserId()).isEqualTo(savedUser.getUserId());
    }
}