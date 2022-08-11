package scheduler.api.domain.an;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private AnnouncementSchedule announcementSchedule; //양방향 의존관계일 필요 없음 n측인 여기에서만 fk 보윤

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
