package org.example.calendar.repository;

import org.assertj.core.api.Assertions;
import org.example.calendar.dto.ScheduleRequestDto;
import org.example.calendar.dto.ScheduleResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScheduleRepositoryImplTest {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    @Transactional
    void repositorySaveTest(){
        ScheduleRequestDto scheduleRequestDto = new ScheduleRequestDto();
        scheduleRequestDto.setTodo("할일 테스트");
        scheduleRequestDto.setUsername("테스트 유저");
        scheduleRequestDto.setPassword("testPassword");

        ScheduleResponseDto scheduleResponseDto = scheduleRepository.saveSchedule(scheduleRequestDto);

        Assertions.assertThat(scheduleResponseDto.getTodo()).isEqualTo(scheduleRequestDto.getTodo());
        Assertions.assertThat(scheduleResponseDto.getUsername()).isEqualTo(scheduleRequestDto.getUsername());

    }
}