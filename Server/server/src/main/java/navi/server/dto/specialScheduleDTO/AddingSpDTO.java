package navi.server.dto.specialScheduleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.enumType.CertificationScheduleType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddingSpDTO {
    private Long id;
    private String name;
    private CertificationScheduleType certificationScheduleType;
    private String s_date;
    private String e_date;
}

