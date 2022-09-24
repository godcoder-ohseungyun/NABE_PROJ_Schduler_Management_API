package scheduler.api.domain.an;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @detail
 * - 서비스에서 제공한 공고 중 사용자가 '구독 신청한 공고'의 내용을 포함하는 도메인 과 '사용자'간 '구독 여부'를 다루는 도메인 입니다.
 * - 각 임베디드 타입에는 고유 검증 로직이 포함 되어 있습니다. * 임베디드 타입 클래스 참고바람
 */
@Entity
@Table(name="announcement_subscription")
@Getter
@Setter
@NoArgsConstructor
public class AnnouncementSubscription {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_schedule_id")
    private AnnouncementSchedule announcementSchedule; //양방향 의존관계일 필요 없음 n측인 여기에서만 fk 보유

    @Column(name = "member_id")
    private Long memberId;

    private AnnouncementSubscription(AnnouncementSchedule announcementSchedule, Long memberId) {
        this.announcementSchedule = announcementSchedule;
        this.memberId = memberId;
    }

    public static AnnouncementSubscription createAnnouncementSubscription(AnnouncementSchedule announcementSchedule, Long memberId){
        return new AnnouncementSubscription(announcementSchedule,memberId);
    }
}
