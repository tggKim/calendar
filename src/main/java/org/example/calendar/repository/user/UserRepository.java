package org.example.calendar.repository.user;

import org.example.calendar.entity.User;

import java.util.Optional;

public interface UserRepository {
    User saveUser(User user);

    Optional<User> findUserById(Long userId);

    int updateUsername(Long userId, String username);

    Optional<String> getUsernameByUserId(Long userId);
}
