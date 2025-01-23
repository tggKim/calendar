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
    void repositorySaveAndFindByIdTest(){
        ScheduleRequestDto scheduleRequestDto = new ScheduleRequestDto();
        scheduleRequestDto.setTodo("할일 테스트");
        scheduleRequestDto.setUsername("테스트 유저");
        scheduleRequestDto.setPassword("testPassword");

        ScheduleResponseDto scheduleResponseDto = scheduleRepository.saveSchedule(scheduleRequestDto);

        ScheduleResponseDto findScheduleResponseDto = scheduleRepository.findScheduleById(scheduleResponseDto.getId()).get();

        Assertions.assertThat(scheduleResponseDto.getId()).isEqualTo(findScheduleResponseDto.getId());
        Assertions.assertThat(scheduleResponseDto.getTodo()).isEqualTo(findScheduleResponseDto.getTodo());
        Assertions.assertThat(scheduleResponseDto.getUsername()).isEqualTo(findScheduleResponseDto.getUsername());
        Assertions.assertThat(scheduleResponseDto.getCreatedDate()).isEqualTo(findScheduleResponseDto.getCreatedDate());
        Assertions.assertThat(scheduleResponseDto.getUpdatedDate()).isEqualTo(findScheduleResponseDto.getUpdatedDate());

    }

}