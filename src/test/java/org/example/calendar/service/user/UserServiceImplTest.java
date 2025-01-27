package org.example.calendar.service.user;

import org.assertj.core.api.Assertions;
import org.example.calendar.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserService service;

    @Test
    @Transactional
    void saveUserandFindUserTest(){
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = service.saveUser(user);

        User findUser = service.findUserById(savedUser.getUserId());

        Assertions.assertThat(findUser.getUserId()).isEqualTo(savedUser.getUserId());
        Assertions.assertThat(findUser.getUsername()).isEqualTo("테스트 유저");
        Assertions.assertThat(findUser.getEmail()).isEqualTo("rl123456@hanmail.net");
        Assertions.assertThat(findUser.getCreatedDate()).isEqualTo(savedUser.getCreatedDate());
        Assertions.assertThat(findUser.getUpdatedDate()).isEqualTo(savedUser.getUpdatedDate());

        Assertions.assertThatThrownBy(() -> service.findUserById(Long.MAX_VALUE)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @Transactional
    void getusernameById(){
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = service.saveUser(user);

        String findUsername = service.getUsernameByUserId(savedUser.getUserId());

        Assertions.assertThat(findUsername).isEqualTo("테스트 유저");

        Assertions.assertThatThrownBy(() -> service.getUsernameByUserId(Long.MAX_VALUE)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @Transactional
    void updateUsernameTest(){
        User user = User.builder()
                .username("테스트 유저")
                .email("rl123456@hanmail.net")
                .build();

        User savedUser = service.saveUser(user);

        try{
            Thread.sleep(1000);
        }catch (Exception e){

        }

        service.updateUsername(savedUser.getUserId(), "업데이트 유저");

        User findUser = service.findUserById(savedUser.getUserId());

        Assertions.assertThat(findUser.getUserId()).isEqualTo(savedUser.getUserId());
        Assertions.assertThat(findUser.getUsername()).isEqualTo("업데이트 유저");
        Assertions.assertThat(findUser.getEmail()).isEqualTo("rl123456@hanmail.net");
        Assertions.assertThat(findUser.getCreatedDate()).isEqualTo(savedUser.getCreatedDate());
        Assertions.assertThat(findUser.getUpdatedDate()).isNotEqualTo(savedUser.getUpdatedDate());
    }
}