package org.example.calendar.service.user;

import org.example.calendar.entity.User;

public interface UserService {
    User saveUser(User user);

    User findUserById(Long userId);

    String getUsernameByUserId(Long userId);

    void updateUsername(Long userId, String username);
}
