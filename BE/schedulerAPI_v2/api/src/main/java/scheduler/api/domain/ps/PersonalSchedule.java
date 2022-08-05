package scheduler.api.domain.ps;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    private String title;

    private String body;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime; //2330 -> 밤 11시 30분

    private String date; //20220726

    @Column(name = "member_id")
    private Long memberId;

    public void updateThisContents(String title,String body){
        this.title = title;
        this.body = body;
    }

    public PersonalSchedule(String title, String body, String startTime, String endTime, String date, Long memberId) {
        this.title = title;
        this.body = body;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.memberId = memberId;
    }
}
