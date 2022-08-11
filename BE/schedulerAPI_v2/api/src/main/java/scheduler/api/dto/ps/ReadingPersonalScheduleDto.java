package scheduler.api.dto.ps;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scheduler.api.domain.embededType.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadingPersonalScheduleDto {
    private String title;
    private String body;
    private String startTime;
    private String endTime;
    private String startDate;

    public ReadingPersonalScheduleDto(String title, String body, StartTime startTime, EndTime endTime, StartDate startDate) {
        this.title = title;
        this.body = body;
        this.startTime = startTime.getStartTime();
        this.endTime = endTime.getEndTime();
        this.startDate = startDate.getStartDate();
    }
}
