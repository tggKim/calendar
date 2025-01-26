package org.example.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.ScheduleRequestDto;
import org.example.calendar.dto.ScheduleResponseDto;
import org.example.calendar.dto.deleteDto.DeleteScheduleRequestDto;
import org.example.calendar.dto.updateDto.UpdateScheduleRequestDto;
import org.example.calendar.entity.Schedule;
import org.example.calendar.service.schedule.ScheduleService;
import org.example.calendar.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable("id") Long id){
        Schedule findSchedule = scheduleService.findScheduleById(id);

        ScheduleResponseDto scheduleResponseDto = ScheduleResponseDto.builder()
                .id(findSchedule.getId())
                .todo(findSchedule.getTodo())
                .username(findSchedule.getUsername())
                .createdDate(findSchedule.getCreatedDate())
                .updatedDate(findSchedule.getUpdatedDate())
                .build();

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(
            @RequestParam(value="userId", required = false) Long userId,
            @RequestParam(value="updatedDate",required = false) String updatedDate,
            @RequestParam(value="sort",required = false) String sort
    ){

        return new ResponseEntity<>(scheduleService.findAllSchedule(userId, updatedDate, sort).stream().map(ScheduleResponseDto::new).toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto){

        // 유저 이름을 가져오면서 userId에 해당하는 유저가 존재하는지 확인
        String username = userService.getUsernameByUserId(scheduleRequestDto.getUserId());

        Schedule savedSchedule = scheduleService.saveSchedule(scheduleRequestDto.toSchedule());

        ScheduleResponseDto scheduleResponseDto = ScheduleResponseDto.builder()
                .id(savedSchedule.getId())
                .todo(savedSchedule.getTodo())
                .username(username)
                .createdDate(savedSchedule.getCreatedDate())
                .updatedDate(savedSchedule.getUpdatedDate())
                .build();

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    // 일부 수정이어서 patch 메서드 사용, 그리고 삭제에서 post 메서드를 사용해서 patch 메서드 사용
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable("id") Long id, @RequestBody UpdateScheduleRequestDto updateScheduleRequestDto){

        // 일정이 존재하는지 비밀번호가 일치하는지 검증
        scheduleService.validatePassword(id, updateScheduleRequestDto.getPassword());

        // 일정의 userId를 가져옴
        Long userId = scheduleService.getUserIdById(id);

        // 유저의 이름 업데이트
        userService.updateUsername(userId, updateScheduleRequestDto.getUsername());

        // 일정의 todo 업데이트
        scheduleService.updateSchedulesTodo(id, updateScheduleRequestDto.getTodo());

        // 업데이트된 일정 가져옴
        Schedule findSchedule = scheduleService.findScheduleById(id);

        ScheduleResponseDto scheduleResponseDto = ScheduleResponseDto.builder()
                .id(findSchedule.getId())
                .todo(findSchedule.getTodo())
                .username(findSchedule.getUsername())
                .createdDate(findSchedule.getCreatedDate())
                .updatedDate(findSchedule.getUpdatedDate())
                .build();

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    // 삭제시 요청 메시지 body에 비밀번호를 담아야 해서 post 메서드 사용
    @PostMapping("/{id}")
    public ResponseEntity deleteSchedule(@PathVariable("id") Long id, @RequestBody DeleteScheduleRequestDto deleteScheduleRequestDto){

        // 일정이 존재하는지, 비밀번호가 일치하는지 검증
        scheduleService.validatePassword(id, deleteScheduleRequestDto.getPassword());

        // 일정 삭제
        scheduleService.deleteScheduleById(id);

        return new ResponseEntity(HttpStatus.OK);

    }
}
