package navi.server.dto.SpecialScheduleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.enumType.CertificationScheduleType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddingSpDTO {
    private String date;
    private String name;
    private CertificationScheduleType certificationScheduleType;
}

