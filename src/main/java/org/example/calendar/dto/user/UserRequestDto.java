package org.example.calendar.dto.user;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.example.calendar.entity.User;

@Getter @Setter
public class UserRequestDto {
    private String username;
    @Email(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",message = "이메일 형식에 맞지 않습니다.")
    private String email;

    public User toUser(){
        return User.builder()
                .username(this.username)
                .email(this.email)
                .build();
    }
}
