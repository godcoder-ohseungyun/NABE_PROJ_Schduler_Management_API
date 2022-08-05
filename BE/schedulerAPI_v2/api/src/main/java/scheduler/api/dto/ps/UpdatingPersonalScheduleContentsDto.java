package scheduler.api.dto.ps;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatingPersonalScheduleContentsDto {

    @NotNull(message = "개인 일정 ID는 필수 값입니다.")
    private Long personalScheduleId;

    @NotBlank(message = "제목은 필수 입력값 입니다.")
    private String title;

    private String body;
}
