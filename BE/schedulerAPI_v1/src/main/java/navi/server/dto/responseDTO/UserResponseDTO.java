package navi.server.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String password;
    private String email;
    private String name; //unique 유저 검색조건으로 쓰일예정임

    private List<AnnouncementSchedule> announcementSchedules; //취업 일정 데이터
    private List<SpecialSchedule> specialSchedules; //특별 일정 데이터
    private List<UserScheduleResponseDTO> userSchedules;
}
