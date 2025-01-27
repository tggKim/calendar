package org.example.calendar.repository.user;

import lombok.RequiredArgsConstructor;
import org.example.calendar.entity.Schedule;
import org.example.calendar.entity.User;
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
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public User saveUser(User user) {
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);

        String sql = "insert into user(username,email,createdDate,updatedDate) values (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder(); // DB에서 직접 생성해준 키값을 받아오기 위해 필요한 keyHolder
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setTimestamp(3, Timestamp.valueOf(localDateTime));
            ps.setTimestamp(4, Timestamp.valueOf(localDateTime));
            return ps;
        }, keyHolder);

        long key = keyHolder.getKey().longValue(); //그다음 keyHolder를 통해 생성된 키값을 꺼내온다.

        return User.builder()
                .userId(key)
                .username(user.getUsername())
                .email(user.getEmail())
                .createdDate(localDateTime)
                .updatedDate(localDateTime)
                .build();
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        String sql = "select userId,username,email,createdDate,updatedDate from user where userId = ?";

        //queryForObject()에서 없는 데이터에 대해 접근하려고하면 EmptyResultDataAccessException가 발생한다.
        try {
            User user = jdbcTemplate.queryForObject(sql, scheduleRowMapper(), userId); // queryForObject는 조회하는게 하나일때사용
            return Optional.of(user); // 값이 있으면 실행되는부분, Optional객체에 담아서 반환

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // 비어있는 Optional객체를 반환
        }
    }

    // 결과를 ScheduleResponseDto 객체에 매핑하기위한 매퍼관련 함수
    private RowMapper<User> scheduleRowMapper() { //jdbcTemplate를 사용할때 resultSet을 매핑하기 위해 필요한 로우매퍼
        return ((rs, rowNum) -> {
            return User.builder()
                    .userId(rs.getLong("userId"))
                    .username(rs.getString("username"))
                    .email(rs.getString("email"))
                    .createdDate(rs.getTimestamp("createdDate").toLocalDateTime())
                    .updatedDate(rs.getTimestamp("updatedDate").toLocalDateTime())
                    .build();
        });
    }

    @Override
    public Optional<String> getUsernameByUserId(Long userId) {
        String sql = "select username from user where userId = ?";
        try{
            String username = jdbcTemplate.queryForObject(sql, String.class, userId);
            return Optional.of(username);
        }catch (EmptyResultDataAccessException e){;
            return Optional.empty();
        }
    }

    @Override
    public int updateUsername(Long userId, String username){
        String sql = "update user set username = ?, updatedDate = ? where userId = ?";
        return jdbcTemplate.update(sql, username, LocalDateTime.now(), userId);
    }

}
