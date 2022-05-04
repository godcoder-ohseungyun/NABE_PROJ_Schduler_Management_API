package navi.server.dto.personalScheduleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatingPsDTO {
    private String oldDate;
    private String newDate;
    private Long targetId;


}
