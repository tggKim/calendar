package org.example.calendar.repository;

import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.ScheduleRequestDto;
import org.example.calendar.dto.ScheduleResponseDto;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {

        LocalDate localDate = LocalDate.now();

        String sql = "insert into schedule(todo,username,password,createdDate,updatedDate) values (?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder(); // DB에서 직접 생성해준 키값을 받아오기 위해 필요한 keyHolder
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, scheduleRequestDto.getTodo());
            ps.setString(2, scheduleRequestDto.getUsername());
            ps.setString(3, scheduleRequestDto.getPassword());
            ps.setDate(4, Date.valueOf(localDate));
            ps.setDate(5, Date.valueOf(localDate));
            return ps;
        }, keyHolder);

        long key = keyHolder.getKey().longValue(); //그다음 keyHolder를 통해 생성된 키값을 꺼내온다.

        return ScheduleResponseDto.builder()
                .id(key)
                .todo(scheduleRequestDto.getTodo())
                .username(scheduleRequestDto.getUsername())
                .createdDate(localDate)
                .updatedDate(localDate)
                .build();
    }
}
