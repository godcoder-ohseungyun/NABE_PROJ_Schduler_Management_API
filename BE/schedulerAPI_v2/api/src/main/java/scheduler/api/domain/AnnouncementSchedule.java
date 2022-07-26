package scheduler.api.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="announcement_schedule")
@Getter
@Setter
public class AnnouncementSchedule {

    @Id
    @GeneratedValue
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
