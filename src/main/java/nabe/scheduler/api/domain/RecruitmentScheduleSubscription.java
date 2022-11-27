package nabe.scheduler.api.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="recruitment_schedule_subscription")
@Getter
@Setter
@NoArgsConstructor
public class RecruitmentScheduleSubscription {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //양방향 의존관계일 필요 없음 n측인 여기에서만 fk 보유
    @JoinColumn(name = "recruitment_schedule_id")
    private RecruitmentSchedule recruitmentSchedule;

    @Column(name = "member_id")
    private Long memberId;

    private RecruitmentScheduleSubscription(RecruitmentSchedule recruitmentSchedule, Long memberId) {
        this.recruitmentSchedule = recruitmentSchedule;
        this.memberId = memberId;
    }

    public static RecruitmentScheduleSubscription createRecruitmentScheduleSubscription(RecruitmentSchedule recruitmentSchedule, Long memberId){
        return new RecruitmentScheduleSubscription(recruitmentSchedule,memberId);
    }
}
