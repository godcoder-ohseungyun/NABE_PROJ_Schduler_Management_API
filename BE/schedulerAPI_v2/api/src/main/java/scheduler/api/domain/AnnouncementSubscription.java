package scheduler.api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="announcement_subscription")
@Getter
@Setter
public class AnnouncementSubscription {

    @Id
    @GeneratedValue
    @Column(name = "an_sub_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "an_id")
    private AnnouncementSchedule announcementSchedule;

    @Column(name = "member_id")
    private Long memberId;
}
