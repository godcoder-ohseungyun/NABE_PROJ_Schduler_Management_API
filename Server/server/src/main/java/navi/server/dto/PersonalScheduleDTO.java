package navi.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalScheduleDTO {
    private String date;
    private String s_time;
    private String e_time;
    private boolean observation;
    private String detail;
}
