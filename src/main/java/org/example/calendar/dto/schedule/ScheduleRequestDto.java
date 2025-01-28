package org.example.calendar.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.calendar.entity.Schedule;

@Getter @Setter
public class ScheduleRequestDto {

    @NotBlank(message = "할 일은 필수 입력 값입니다.")
    @Size(max=200, message = "최대 200자 까지 허용됩니다.")
    private String todo;

    private Long userId;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    public Schedule toSchedule(){
        return Schedule.builder()
                .todo(this.todo)
                .userId(this.userId)
                .password(this.password)
                .build();
    }
}
