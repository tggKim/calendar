package org.example.calendar.dto.schedule.deleteDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DeleteScheduleRequestDto {
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
}
