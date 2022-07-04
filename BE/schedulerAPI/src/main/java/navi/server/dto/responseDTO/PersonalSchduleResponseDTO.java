package navi.server.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalSchduleResponseDTO {
    private Long id;
    private String s_time;
    private String e_time;
    private boolean observation; //모니터링 필요시 ui에 d-day line 제공
    private String detail;
}
