package nabe.scheduler.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatingPersonalScheduleDto {
    private String title;
    private String body;
    private String startTime;
    private String endTime;
    private String startDate;
}
