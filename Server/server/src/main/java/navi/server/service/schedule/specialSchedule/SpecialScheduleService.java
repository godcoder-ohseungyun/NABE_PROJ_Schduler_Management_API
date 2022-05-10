package navi.server.service.schedule.specialSchedule;

import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;

public interface SpecialScheduleService {
    SpecialSchedule createSpecialSchedule(SpecialSchedule scheduleService);
    void deleteSpecialSchedule(Long id);
    SpecialSchedule findSpecialSchedule(Long spId);
    boolean isIn(Long spId);
}
