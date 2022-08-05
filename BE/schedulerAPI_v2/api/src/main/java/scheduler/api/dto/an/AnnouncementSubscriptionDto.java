package scheduler.api.dto.an;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementSubscriptionDto {

    private Long announcementSubscriptionId;

    private String title;
    private String originalUrl;
    private String startDate;
    private String endDate;

}
