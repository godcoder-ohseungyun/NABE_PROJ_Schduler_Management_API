package navi.server.repository.schedule.UserScheduleSubclasses;


import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;

public interface AnnouncementScheduleRepository {
    AnnouncementSchedule save(AnnouncementSchedule announcementSchedule);

    AnnouncementSchedule findById(String anId);


    void delete(String anId);

    void clearStore();
}
