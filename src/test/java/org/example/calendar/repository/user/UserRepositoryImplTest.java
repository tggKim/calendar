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

    // user 저장과 조회 검증
    @Test
    @Transactional
    void userSaveTest(){
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = repository.saveUser(user);

        User findUser = repository.findUserById(savedUser.getUserId()).get();

        Assertions.assertThat(findUser.getUserId()).isEqualTo(savedUser.getUserId());
        Assertions.assertThat(findUser.getUsername()).isEqualTo("테스트 유저");
        Assertions.assertThat(findUser.getEmail()).isEqualTo("rl123456@hanmail.net");
        Assertions.assertThat(findUser.getCreatedDate()).isEqualTo(savedUser.getCreatedDate());
        Assertions.assertThat(findUser.getUpdatedDate()).isEqualTo(savedUser.getUpdatedDate());
    }

    // userId로 username 가져오기 테스트
    @Test
    @Transactional
    void getUsernameTest(){
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = repository.saveUser(user);

        String findUsername = repository.getUsernameByUserId(savedUser.getUserId()).get();

        Assertions.assertThat(findUsername).isEqualTo("테스트 유저");
    }

    // username 업데이트 테스트
    @Test
    @Transactional
    void updateUsernameTest(){
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = repository.saveUser(user);

        try{
            Thread.sleep(1000);
        }catch (Exception e){

        }

        repository.updateUsername(savedUser.getUserId(), "업데이트 유저");

        User findUser = repository.findUserById(savedUser.getUserId()).get();

        Assertions.assertThat(findUser.getUserId()).isEqualTo(savedUser.getUserId());
        Assertions.assertThat(findUser.getUsername()).isEqualTo("업데이트 유저");
        Assertions.assertThat(findUser.getEmail()).isEqualTo("rl123456@hanmail.net");
        Assertions.assertThat(findUser.getCreatedDate()).isEqualTo(savedUser.getCreatedDate());
        Assertions.assertThat(findUser.getUpdatedDate()).isNotEqualTo(savedUser.getUpdatedDate());
    }
}