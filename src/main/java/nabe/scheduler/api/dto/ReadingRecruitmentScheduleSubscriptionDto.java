package nabe.scheduler.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nabe.scheduler.api.domain.embededType.EndDate;
import nabe.scheduler.api.domain.embededType.OriginalUrl;
import nabe.scheduler.api.domain.embededType.StartDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadingRecruitmentScheduleSubscriptionDto {
    private Long recruitmentScheduleSubscriptionId;
    private String title;
    private String originalUrl;
    private String startDate;
    private String endDate;

    public ReadingRecruitmentScheduleSubscriptionDto(Long recruitmentScheduleSubscriptionId,
                                                     String title,
                                                     OriginalUrl originalUrl,
                                                     StartDate startDate,
                                                     EndDate endDate) {
        this.recruitmentScheduleSubscriptionId = recruitmentScheduleSubscriptionId;
        this.title = title;
        this.originalUrl = originalUrl.getOriginalUrl();
        this.startDate = startDate.getStartDate();
        this.endDate = endDate.getEndDate();

    }
}
