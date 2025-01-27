package org.example.calendar.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.example.calendar.entity.User;

@Getter @Setter
public class UserRequestDto {
    private String username;
    private String email;

    public User toUser(){
        return User.builder()
                .username(this.username)
                .email(this.email)
                .build();
    }
}
