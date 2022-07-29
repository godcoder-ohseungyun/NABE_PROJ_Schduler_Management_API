package scheduler.api.domain.an;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="announcement_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementSchedule {

    @Id
    //@GeneratedValue 이부분 주석 으로 처리한 이유 정리해야함
    @Column(name = "an_id")
    private Long id;

    private String title;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate; //2330 -> 밤 11시 30분


}
