package navi.server.domain.schedule.userScheduleSubclasses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalSchedule {
    //private String date = "2022-04-29";
    //private String owner = "userId";
    private Long id;
    private String s_time = "08:30";
    private String e_time = "09:30";
    private boolean observation = true; //모니터링 필요시 ui에 d-day line 제공
    private String detail = "내용";

    public PersonalSchedule(String s_time, String e_time, boolean observation, String detail) {
        this.id = 0l;
        this.s_time = s_time;
        this.e_time = e_time;
        this.observation = observation;
        this.detail = detail;
    }
}
