package org.example.calendar.service.user;

import lombok.RequiredArgsConstructor;
import org.example.calendar.entity.User;
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
    public String getUsernameByUserId(Long userId) {
        return userRepository.getUsernameByUserId(userId).orElseThrow(() -> new NoSuchElementException("userId에 해당하는 유저가 없습니다."));
    }

    @Override
    public boolean existByUserId(Long userId) {
        if(!userRepository.existsByUserId(userId)){
            throw new NoSuchElementException("userId에 해당하는 유저가 없습니다.");
        }
        return true;
    }

    @Override
    public void updateUsername(Long userId, String username) {
        userRepository.updateUsername(userId, username);
    }
}
