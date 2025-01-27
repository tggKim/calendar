package org.example.calendar.controller.user;

import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.user.UserResponseDto;
import org.example.calendar.entity.User;
import org.example.calendar.service.user.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserByUserId(@PathVariable("userId") Long userId){
        User findUser = userService.findUserById(userId);
        return new ResponseEntity<>(new UserResponseDto(findUser), HttpStatus.OK);
    }
}
