package navi.server.dto.personalScheduleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatingPsDTO {
    private Long targetId;
    private String s_time;
    private String e_time;
    private boolean observation;
    private String detail;


}
