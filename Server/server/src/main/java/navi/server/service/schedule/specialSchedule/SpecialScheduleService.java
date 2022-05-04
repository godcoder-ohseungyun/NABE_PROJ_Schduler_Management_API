package navi.server.service.schedule.specialSchedule;

import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;

public interface SpecialScheduleService {
    SpecialSchedule createSpecialScheduleService(SpecialSchedule scheduleService);
    void deleteSpecialScheduleService(Long id);
}
