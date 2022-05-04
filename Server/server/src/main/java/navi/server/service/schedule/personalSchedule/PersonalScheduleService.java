package navi.server.service.schedule.personalSchedule;

import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;

public interface PersonalScheduleService {
    PersonalSchedule createPersonalSchedule(PersonalSchedule personalSchedule);
    void deletePersonalSchedule(Long id);
    void updatePersonalSchedule(Long id,PersonalSchedule personalSchedule);
}
