package navi.server.domain.schedule.userScheduleSubclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.enumType.CertificationScheduleType;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialSchedule {
    private String name; //pk
    private String test_date; //pk
    private String s_date;
    private String e_date;
    private String r_date;

    public SpecialSchedule(String name,
                           String s_date,
                           String e_date,
                           String r_date) {
        this.name = name;
        this.s_date = s_date;
        this.e_date = e_date;
        this.r_date = r_date;
    }
}

