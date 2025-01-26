package org.example.calendar.repository.schedule;

import lombok.RequiredArgsConstructor;
import org.example.calendar.entity.Schedule;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    // 저장
    @Override
    public Schedule saveSchedule(Schedule schedule) {

        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);

        String sql = "insert into schedule(todo,userId,password,createdDate,updatedDate) values (?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder(); // DB에서 직접 생성해준 키값을 받아오기 위해 필요한 keyHolder
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, schedule.getTodo());
            ps.setLong(2, schedule.getUserId());
            ps.setString(3, schedule.getPassword());
            ps.setTimestamp(4, Timestamp.valueOf(localDateTime));
            ps.setTimestamp(5, Timestamp.valueOf(localDateTime));
            return ps;
        }, keyHolder);

        long key = keyHolder.getKey().longValue(); //그다음 keyHolder를 통해 생성된 키값을 꺼내온다.

        return Schedule.builder()
                .id(key)
                .todo(schedule.getTodo())
                .userId(schedule.getUserId())
                .createdDate(localDateTime)
                .updatedDate(localDateTime)
                .build();
    }

    // 단건 조회해서 있으면 Optional에 담아서 리턴, 없으면 빈 Optional 리턴
    @Override
    public Optional<Schedule> findScheduleById(Long id){
        String sql = "select s.id, s.todo, s.userId, u.username, s.createdDate, s.updatedDate from schedule s inner join user u on s.userId = u.userId where s.id = ?";

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
    public List<Schedule> findAllSchedule(Long userId, String updatedDate, String sort){
        String updateDatePattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
        if(updatedDate != null && !Pattern.matches(updateDatePattern, updatedDate)){
            throw new IllegalArgumentException("올바르지 않은 날짜 형식입니다.");
        }

        String sql = "";
        if(userId != null && updatedDate == null && sort == null){
            sql = "select s.id, s.todo, s.userId, u.username, s.createdDate, s.updatedDate from schedule s inner join user u on s.userId = u.userId where s.userId = ?";
            return jdbcTemplate.query(sql, scheduleRowMapper(), userId);
        }
        else if(userId == null && updatedDate != null && sort == null){
            sql = "select s.id, s.todo, s.userId, u.username, s.createdDate, s.updatedDate from schedule s inner join user u on s.userId = u.userId where date(s.updatedDate) = ?";
            return jdbcTemplate.query(sql, scheduleRowMapper(), updatedDate);
        }
        else if(userId != null && updatedDate != null && sort == null){
            sql = "select s.id, s.todo, s.userId, u.username, s.createdDate, s.updatedDate from schedule s inner join user u on s.userId = u.userId where s.userId = ? and date(s.updatedDate) = ?";
            return jdbcTemplate.query(sql, scheduleRowMapper(), userId, updatedDate);
        }
        else if(userId == null && updatedDate == null && sort != null){
            String sortPattern = "^(id|todo|createdDate|updatedDate)\\.(ASC|DESC|asc|desc)$";
            if(Pattern.matches(sortPattern, sort)){
                String[] strs = sort.split("\\.");
                sql = "select s.id, s.todo, s.userId, u.username, s.createdDate, s.updatedDate from schedule s inner join user u on s.userId = u.userId order by " + strs[0] + " " + strs[1];
                return jdbcTemplate.query(sql, scheduleRowMapper());
            }

            sql = "select s.id, s.todo, s.userId, u.username, s.createdDate, s.updatedDate from schedule s inner join user u on s.userId = u.userId order by s.id";
            return jdbcTemplate.query(sql, scheduleRowMapper());
        }
        else{
            sql = "select s.id, s.todo, s.userId, u.username, s.createdDate, s.updatedDate from schedule s inner join user u on s.userId = u.userId order by s.id";
            return jdbcTemplate.query(sql, scheduleRowMapper());
        }

    }

    // 결과를 ScheduleResponseDto 객체에 매핑하기위한 매퍼관련 함수
    private RowMapper<Schedule> scheduleRowMapper() { //jdbcTemplate를 사용할때 resultSet을 매핑하기 위해 필요한 로우매퍼
        return ((rs, rowNum) -> {
            return Schedule.builder()
                    .id(rs.getLong("id"))
                    .todo(rs.getString("todo"))
                    .userId(rs.getLong("userId"))
                    .username(rs.getString("username"))
                    .createdDate(rs.getTimestamp("createdDate").toLocalDateTime())
                    .updatedDate(rs.getTimestamp("updatedDate").toLocalDateTime())
                    .build();
        });
    }

    // 업데이트하고 해당 컬럼 번호를 리턴, 0을 리턴하면 id에 해당되는 일정이 없는 것
    @Override
    public int updateSchedulesTodo(Long id, String todo){
        String sql = "update schedule set todo = ?, updatedDate = ? where id = ?";
        return jdbcTemplate.update(sql, todo, LocalDateTime.now(), id);
    }

    // id에 해당하는 일정 삭제
    @Override
    public int deleteScheduleById(Long id){
        String sql = "delete from schedule where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // id에 해당하는 비밀번호를 가져오는 메서드
    @Override
    public Optional<String> getPasswordById(Long id){
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

    @Override
    public Optional<Long> getUserIdById(Long id) {
        String sql = "select userId from schedule where id = ?";
        try{
            Long findUserId = jdbcTemplate.queryForObject(sql, Long.class, id);
            return Optional.of(findUserId);
        }catch (EmptyResultDataAccessException e){;
            return Optional.empty();
        }
    }
}
