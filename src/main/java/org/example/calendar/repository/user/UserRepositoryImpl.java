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

    // userId로 유저가 존재하는지 판단하는 메서드
    @Override
    public boolean existsByUserId(Long userId) {
        String sql = "select userId from user where userId = ?";
        try{
            jdbcTemplate.queryForObject(sql, Long.class, userId);
            return true;
        }catch (EmptyResultDataAccessException e){;
            return false;
        }
    }

}
