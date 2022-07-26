package navi.server.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserScheduleResponseDTO {
    private Long id;
    private String date;

    private List<PersonalSchduleResponseDTO> personalSchedules; //개인 일정 데이터
}
