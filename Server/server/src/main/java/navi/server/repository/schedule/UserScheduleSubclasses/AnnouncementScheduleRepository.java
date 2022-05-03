package navi.server.repository.schedule.UserScheduleSubclasses;


import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;

public interface AnnouncementScheduleRepository {
    AnnouncementSchedule save(AnnouncementSchedule announcementSchedule);

    AnnouncementSchedule findById(Long id);
    
    AnnouncementSchedule update(String LoginId, AnnouncementSchedule updateParam);

    void delete(Long id);

    void clearStore();
}
