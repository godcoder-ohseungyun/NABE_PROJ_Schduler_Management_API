package navi.server.repository.schedule.announcementSchedule;


import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;

public interface AnnouncementScheduleRepository {
    AnnouncementSchedule save(AnnouncementSchedule announcementSchedule);

    AnnouncementSchedule findById(String anId);


    void delete(String anId);

    void clearStore();
}
