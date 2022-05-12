package navi.server.dto.specialScheduleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.enumType.CertificationScheduleType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddingSpDTO {
    private String name;
    private String test_date;
    private String r_date;
    private String s_date;
    private String e_date;
}

