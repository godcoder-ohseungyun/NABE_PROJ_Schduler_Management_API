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

    public SpecialSchedule(String name, CertificationScheduleType certificationScheduleType) {
        this.id = 0l;
        this.name = name;
        this.certificationScheduleType = certificationScheduleType;
    }
}

