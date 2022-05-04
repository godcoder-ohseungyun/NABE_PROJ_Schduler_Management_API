package navi.server.service.schedule.announcementSchedule;

import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;

public interface AnnouncementScheduleService {
    AnnouncementSchedule createAnnouncementSchedule(AnnouncementSchedule announcementSchedule);
    void deleteAnnouncementSchedule(String anId);
    AnnouncementSchedule findAnnouncementSchedule(String anId);

}
