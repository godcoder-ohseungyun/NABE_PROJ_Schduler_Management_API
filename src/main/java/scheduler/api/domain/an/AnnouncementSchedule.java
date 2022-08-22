package scheduler.api.domain.an;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scheduler.api.domain.embededType.EndDate;
import scheduler.api.domain.embededType.OriginalUrl;
import scheduler.api.domain.embededType.StartDate;
import scheduler.api.exception.exceptionDomain.ValidatedException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="announcement_schedule")
@Getter
@Setter
@NoArgsConstructor
public class AnnouncementSchedule {

    @Id
    //@GeneratedValue 이부분 주석 으로 처리한 이유 정리해야함
    @Column(name = "id")
    private Long id;

    @NotNull( message = "제목은 필수 입력 값 입니다.")
    private String title;

    @Column(name = "original_url")
    @Embedded
    private OriginalUrl originalUrl;

    @Column(name = "start_date")
    @Embedded
    private StartDate startDate;

    @Column(name = "end_date")
    @Embedded
    private EndDate endDate;

    private AnnouncementSchedule(Long id, String title, OriginalUrl originalUrl, StartDate startDate, EndDate endDate) {
        this.id = id;
        this.title = title;
        this.originalUrl = originalUrl;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static AnnouncementSchedule createAnnouncementSchedule(Long id, String title, String originalUrl, String startDate, String endDate) throws ValidatedException {
        return new AnnouncementSchedule(id,
                title,
                OriginalUrl.from(originalUrl),
                StartDate.from(startDate),
                EndDate.from(endDate));
    }

}
