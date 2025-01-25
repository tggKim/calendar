package org.example.calendar.service.user;

import lombok.RequiredArgsConstructor;
import org.example.calendar.entity.User;
import org.example.calendar.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }
}
