package navi.server.repository.schedule.userSchedule.personalSchedule;

import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;

public interface PersonalScheduleRepository {
    PersonalSchedule save(PersonalSchedule personalSchedule);

    PersonalSchedule findById(Long id);

    PersonalSchedule update(Long id, PersonalSchedule update);

    void delete(Long id);

    void clearStore();
}
