package org.example.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.ScheduleRequestDto;
import org.example.calendar.dto.ScheduleResponseDto;
import org.example.calendar.dto.deleteDto.DeleteScheduleRequestDto;
import org.example.calendar.dto.updateDto.UpdateScheduleRequestDto;
import org.example.calendar.entity.Schedule;
import org.example.calendar.service.schedule.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable("id") Long id){
        Schedule findSchedule = scheduleService.findScheduleById(id);
        return new ResponseEntity<>(new ScheduleResponseDto(findSchedule), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(
            @RequestParam(value="username", required = false) String username,
            @RequestParam(value="updatedDate",required = false) String updatedDate,
            @RequestParam(value="sort",required = false) String sort
    ){

        return new ResponseEntity<>(scheduleService.findAllSchedule(username, updatedDate, sort).stream().map(ScheduleResponseDto::new).toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto){
        Schedule savedSchedule = scheduleService.saveSchedule(scheduleRequestDto.toSchedule());
        return new ResponseEntity<>(new ScheduleResponseDto(savedSchedule), HttpStatus.OK);
    }

    // 일부 수정이어서 patch 메서드 사용, 그리고 삭제에서 post 메서드를 사용해서 patch 메서드 사용
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable("id") Long id, @RequestBody UpdateScheduleRequestDto updateScheduleRequestDto){
        Schedule schedule = Schedule.builder()
                .id(id)
                .todo(updateScheduleRequestDto.getTodo())
                .username(updateScheduleRequestDto.getUsername())
                .password(updateScheduleRequestDto.getPassword())
                .build();

        Schedule updatedSchedule = scheduleService.updateSchedulesTodoAndUsername(schedule);

        return new ResponseEntity<>(new ScheduleResponseDto(updatedSchedule), HttpStatus.OK);
    }

    // 삭제시 요청 메시지 body에 비밀번호를 담아야 해서 post 메서드 사용
    @PostMapping("/{id}")
    public ResponseEntity deleteSchedule(@PathVariable("id") Long id, @RequestBody DeleteScheduleRequestDto deleteScheduleRequestDto){
        scheduleService.deleteScheduleById(id, deleteScheduleRequestDto.getPassword());
        return new ResponseEntity(HttpStatus.OK);
    }
}
