package scheduler.api.dto.an;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scheduler.api.domain.embededType.EndDate;
import scheduler.api.domain.embededType.OriginalUrl;
import scheduler.api.domain.embededType.StartDate;

import javax.persistence.Embedded;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadingAnnouncementSubscriptionDto {
    private Long announcementSubscriptionId;
    private String title;
    private String originalUrl;
    private String startDate;
    private String endDate;

    public ReadingAnnouncementSubscriptionDto(Long announcementSubscriptionId,
                                              String title,
                                              OriginalUrl originalUrl,
                                              StartDate startDate,
                                              EndDate endDate) {
        this.announcementSubscriptionId = announcementSubscriptionId;
        this.title = title;
        this.originalUrl = originalUrl.getOriginalUrl();
        this.startDate = startDate.getStartDate();
        this.endDate = endDate.getEndDate();

    }
}
