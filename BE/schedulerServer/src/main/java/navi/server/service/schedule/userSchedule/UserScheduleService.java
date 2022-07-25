package navi.server.service.schedule.userSchedule;

import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.user.User;

public interface UserScheduleService {
    UserSchedule createUserSchedule(UserSchedule userSchedule);
    void deleteUserSchedule(Long id);

}
