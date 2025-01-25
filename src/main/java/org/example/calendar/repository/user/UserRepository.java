package org.example.calendar.repository.user;

import org.example.calendar.entity.User;

import java.util.Optional;

public interface UserRepository {
    User saveUser(User user);

    boolean existsByUserId(Long userId);

    Optional<String> getUsernameById(Long userId);
}
