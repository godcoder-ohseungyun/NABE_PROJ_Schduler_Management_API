package scheduler.api.dto.ps;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatingPersonalScheduleDto {
    private String title;
    private String body;
    private String startTime;
    private String endTime; //2330 -> 밤 11시 30분
    private String startDate; //20220726
}
