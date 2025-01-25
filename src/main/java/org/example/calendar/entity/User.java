package org.example.calendar.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private Long userId;
    private String username;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public User(Long userId, String username, String email, LocalDateTime createdDate, LocalDateTime updatedDate){
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
