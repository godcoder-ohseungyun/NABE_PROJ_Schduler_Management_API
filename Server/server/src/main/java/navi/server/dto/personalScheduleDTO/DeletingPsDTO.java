package navi.server.dto.personalScheduleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeletingPsDTO {
    private String targetDate;
    private Long targetId;
}
