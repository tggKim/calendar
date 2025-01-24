package org.example.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.ScheduleRequestDto;
import org.example.calendar.dto.ScheduleResponseDto;
import org.example.calendar.entity.Schedule;
import org.example.calendar.service.ScheduleService;
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
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(){
        return new ResponseEntity<>(scheduleService.findAllSchedule().stream().map(ScheduleResponseDto::new).toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto){
        Schedule savedSchedule = scheduleService.saveSchedule(scheduleRequestDto.toSchedule());
        return new ResponseEntity<>(new ScheduleResponseDto(savedSchedule), HttpStatus.OK);
    }

    //@PostMapping
    //public ResponseEntity<ScheduleResponseDto> updateSchedule(@RequestBody)
}
