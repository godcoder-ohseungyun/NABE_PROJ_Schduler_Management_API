package navi.server.dto.personalScheduleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatingPsDTO {
    private String date;
    private String s_time;
    private String e_time;
    private boolean observation;
    private String detail;
}
