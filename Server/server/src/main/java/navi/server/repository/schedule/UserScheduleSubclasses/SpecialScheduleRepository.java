package navi.server.repository.schedule.UserScheduleSubclasses;

import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;

public interface SpecialScheduleRepository {
    SpecialSchedule save(SpecialSchedule SpecialSchedule);

    SpecialSchedule findById(Long id);

    void delete(Long id);

    void clearStore();
}
