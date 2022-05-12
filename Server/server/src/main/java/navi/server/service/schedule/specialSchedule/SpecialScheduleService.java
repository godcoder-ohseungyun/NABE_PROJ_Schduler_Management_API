package navi.server.service.schedule.specialSchedule;

import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;

public interface SpecialScheduleService {
    SpecialSchedule createSpecialSchedule(SpecialSchedule scheduleService);
    void deleteSpecialSchedule(String spId);
    SpecialSchedule findSpecialSchedule(String spId);
    boolean isIn(String spId);
}
