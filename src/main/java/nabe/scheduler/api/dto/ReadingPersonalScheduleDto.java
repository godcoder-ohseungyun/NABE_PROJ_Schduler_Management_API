package nabe.scheduler.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nabe.scheduler.api.domain.embededType.EndTime;
import nabe.scheduler.api.domain.embededType.StartDate;
import nabe.scheduler.api.domain.embededType.StartTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadingPersonalScheduleDto {

    private Long id;
    private String title;
    private String body;
    private String startTime;
    private String endTime;
    private String startDate;

    public ReadingPersonalScheduleDto(Long id, String title, String body, StartTime startTime, EndTime endTime, StartDate startDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.startTime = startTime.getStartTime();
        this.endTime = endTime.getEndTime();
        this.startDate = startDate.getStartDate();
    }
}
