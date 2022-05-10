package navi.server.dto.personalScheduleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeletingDTO {
    private String targetDate;
    private Long targetId;
}
