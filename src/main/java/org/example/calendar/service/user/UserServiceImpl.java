package org.example.calendar.service.user;

import lombok.RequiredArgsConstructor;
import org.example.calendar.entity.User;
import org.example.calendar.error.ErrorCode;
import org.example.calendar.exception.Exception404;
import org.example.calendar.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findUserById(userId).orElseThrow(() -> new Exception404(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public String getUsernameByUserId(Long userId) {
        return userRepository.getUsernameByUserId(userId).orElseThrow(() -> new Exception404(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public void updateUsername(Long userId, String username) {
        userRepository.updateUsername(userId, username);
    }
}
