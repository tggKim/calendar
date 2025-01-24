package org.example.calendar.repository;

import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.ScheduleRequestDto;
import org.example.calendar.dto.ScheduleResponseDto;
import org.example.calendar.entity.Schedule;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    // 저장
    @Override
    public Schedule saveSchedule(Schedule schedule) {

        LocalDate localDate = LocalDate.now();

        String sql = "insert into schedule(todo,username,password,createdDate,updatedDate) values (?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder(); // DB에서 직접 생성해준 키값을 받아오기 위해 필요한 keyHolder
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, schedule.getTodo());
            ps.setString(2, schedule.getUsername());
            ps.setString(3, schedule.getPassword());
            ps.setDate(4, Date.valueOf(localDate));
            ps.setDate(5, Date.valueOf(localDate));
            return ps;
        }, keyHolder);

        long key = keyHolder.getKey().longValue(); //그다음 keyHolder를 통해 생성된 키값을 꺼내온다.

        return Schedule.builder()
                .id(key)
                .todo(schedule.getTodo())
                .username(schedule.getUsername())
                .createdDate(localDate)
                .updatedDate(localDate)
                .build();
    }

    // 단건 조회해서 있으면 Optional에 담아서 리턴, 없으면 빈 Optional 리턴
    @Override
    public Optional<Schedule> findScheduleById(Long id){
        String sql = "select id,todo,username,createdDate,updatedDate from schedule where id = ?";

        //queryForObject()에서 없는 데이터에 대해 접근하려고하면 EmptyResultDataAccessException가 발생한다.
        try {
            Schedule schedule = jdbcTemplate.queryForObject(sql, scheduleRowMapper(), id); // queryForObject는 조회하는게 하나일때사용
            return Optional.of(schedule); // 값이 있으면 실행되는부분, Optional객체에 담아서 반환

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // 비어있는 Optional객체를 반환
        }

    }

    // 모든 일정 불러옴 없으면 빈 리스트 반환
    @Override
    public List<Schedule> findAllSchedule(){
        String sql = "select id,todo,username,createdDate,updatedDate from schedule";
        return jdbcTemplate.query(sql, scheduleRowMapper());
    }

    // 결과를 ScheduleResponseDto 객체에 매핑하기위한 매퍼관련 함수
    private RowMapper<Schedule> scheduleRowMapper() { //jdbcTemplate를 사용할때 resultSet을 매핑하기 위해 필요한 로우매퍼
        return ((rs, rowNum) -> {
            return Schedule.builder()
                    .id(rs.getLong("id"))
                    .todo(rs.getString("todo"))
                    .username(rs.getString("username"))
                    .createdDate(rs.getDate("createdDate").toLocalDate())
                    .updatedDate(rs.getDate("updatedDate").toLocalDate())
                    .build();
        });
    }

    // 업데이트하고 해당 컬럼 번호를 리턴, 0을 리턴하면 id에 해당되는 일정이 없는 것
    @Override
    public int updateSchedulesTodoAndUsername(Long id, String todo, String username){
        String sql = "update schedule set todo = ?, username = ?, updatedDate = ? where id = ?";
        return jdbcTemplate.update(sql, todo, username, Date.valueOf(LocalDate.now()),id);
    }

    // id에 해당하는 일정 삭제
    @Override
    public int deleteScheduleById(Long id){
        String sql = "delete from schedule where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // id에 해당하는 비밀번호를 가져오는 메서드
    @Override
    public Optional<String> getUserPasswordById(Long id){
        String sql = "select password from schedule where id = ?";
        try{
            String findPassword = jdbcTemplate.queryForObject(sql, passwordRowMapper(), id);
            return Optional.of(findPassword);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    private RowMapper<String> passwordRowMapper() {
        return ((rs, rowNum) -> {
            return rs.getString("password");
        });
    }
}
