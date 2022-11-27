package nabe.scheduler.api.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nabe.scheduler.api.domain.embededType.EndDate;
import nabe.scheduler.api.domain.embededType.OriginalUrl;
import nabe.scheduler.api.domain.embededType.StartDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="recruitment_schedule")
@Getter
@Setter
@NoArgsConstructor
public class RecruitmentSchedule {

    @Id
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

    private RecruitmentSchedule(
            Long id
            , String title
            , OriginalUrl originalUrl
            , StartDate startDate
            , EndDate endDate) {
        this.id = id;
        this.title = title;
        this.originalUrl = originalUrl;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static RecruitmentSchedule createRecruitmentSchedule(
            Long id
            , String title
            , String originalUrl
            , String startDate
            , String endDate) {
        return new RecruitmentSchedule(
                id
                , title
                , OriginalUrl.from(originalUrl)
                , StartDate.from(startDate)
                , EndDate.from(endDate));
    }

}
