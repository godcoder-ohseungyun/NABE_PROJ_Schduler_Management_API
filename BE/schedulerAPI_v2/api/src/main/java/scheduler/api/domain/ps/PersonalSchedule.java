package scheduler.api.domain.ps;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scheduler.api.domain.embededType.EndTime;
import scheduler.api.domain.embededType.StartDate;
import scheduler.api.domain.embededType.StartTime;
import scheduler.api.exception.exceptionDomain.ValidatedException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="personal_schedule")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalSchedule {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotNull( message = "제목은 필수 입력 값 입니다.")
    private String title;

    private String body;

    @Column(name = "start_time")
    @Embedded
    private StartTime startTime;

    @Column(name = "end_time")
    @Embedded
    private EndTime endTime;

    private StartDate startDate;

    @Column(name = "member_id")
    private Long memberId;

    public void updateThisContents(String title,String body){
        this.title = title;
        this.body = body;
    }

    private PersonalSchedule(String title, String body, StartTime startTime, EndTime endTime, StartDate startDate, Long memberId) {
        this.title = title;
        this.body = body;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.memberId = memberId;
    }

    public static PersonalSchedule createPersonalSchedule(String title, String body, String startTime, String endTime, String startDate, Long memberId) throws ValidatedException {
        return new PersonalSchedule(title,
                body,
                StartTime.from(startTime),
                EndTime.from(endTime),
                StartDate.from(startDate),
                memberId);
    }
}
