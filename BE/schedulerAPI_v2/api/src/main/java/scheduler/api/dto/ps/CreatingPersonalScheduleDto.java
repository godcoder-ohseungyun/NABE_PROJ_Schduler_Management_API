package scheduler.api.dto.ps;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatingPersonalScheduleDto {
    @NotBlank(message = " 제목은 필수 입력값 입니다.")
    private String title;

    private String body;

    @NotBlank(message = " 시작시간은 필수 입력값 입니다.")
    @Size(min=4,max=4, message = "유효하지 않는 시간")
    private String startTime;

    @NotBlank(message = " 종료시간은 필수 입력값 입니다.")
    @Size(min=4,max=4, message = "유효하지 않는 시간")
    private String endTime; //2330 -> 밤 11시 30분

    @NotBlank(message = " 날짜는 필수 입력값 입니다.")
    @Size(min=8,max=8,message = "유효하지 않는 날짜")
    private String date; //20220726
}
