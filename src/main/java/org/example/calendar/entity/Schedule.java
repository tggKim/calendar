package org.example.calendar.entity;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Schedule {
    private Long id;
    private String todo;
    private String username;
    private String password;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    // todo와 username을 업데이트
    public void updateTodoAndUsername(String todo, String username){
        this.todo = todo;
        this.username = username;
    }

    // 수정될때 updatedDate 업데이트
    public void updateUpdatedDate(LocalDate updatedDate){
        this.updatedDate = updatedDate;
    }
}
