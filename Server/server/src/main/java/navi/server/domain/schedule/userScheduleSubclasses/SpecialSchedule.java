package navi.server.domain.schedule.userScheduleSubclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.enumType.CertificationScheduleType;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialSchedule {
    private Long id;
    private String name;
    private CertificationScheduleType certificationScheduleType;
    private String s_date;
    private String e_date;

    public SpecialSchedule(String name,
                           CertificationScheduleType certificationScheduleType,
                           String s_date,
                           String e_date) {
        this.name = name;
        this.certificationScheduleType = certificationScheduleType;
        this.s_date = s_date;
        this.e_date = e_date;
    }
}

