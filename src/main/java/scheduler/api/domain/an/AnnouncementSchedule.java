package scheduler.api.domain.an;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scheduler.api.domain.embededType.EndDate;
import scheduler.api.domain.embededType.OriginalUrl;
import scheduler.api.domain.embededType.StartDate;
import scheduler.api.exception.userDefinedException.ValidatedException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @detail
 * - 서비스에서 제공한 공고 중 사용자가 구독 신청한 공고의 내용을 다루는 도메인 입니다.
 * - 각 임베디드 타입에는 고유 검증 로직이 포함 되어 있습니다. * 임베디드 타입 클래스 참고바람
 */
@Entity
@Table(name="announcement_schedule")
@Getter
@Setter
@NoArgsConstructor
public class AnnouncementSchedule {

    @Id
    //@GeneratedValue
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
