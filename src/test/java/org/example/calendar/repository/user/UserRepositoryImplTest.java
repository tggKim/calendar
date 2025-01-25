package org.example.calendar.repository.user;

import org.assertj.core.api.Assertions;
import org.example.calendar.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryImplTest {
    @Autowired
    UserRepository repository;

    @Test
    @Transactional
    void userSaveTest(){
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = repository.saveUser(user);

        Assertions.assertThat(repository.existsByUserId(savedUser.getUserId())).isTrue();
    }

}