package navi.server.repository.schedule.UserScheduleSubclasses;

import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;

public interface PersonalScheduleRepository {
    PersonalSchedule save(PersonalSchedule personalSchedule);

    PersonalSchedule findById(Long id);

    PersonalSchedule update(String LoginId, PersonalSchedule updateParam);

    void delete(Long id);

    void clearStore();
}
