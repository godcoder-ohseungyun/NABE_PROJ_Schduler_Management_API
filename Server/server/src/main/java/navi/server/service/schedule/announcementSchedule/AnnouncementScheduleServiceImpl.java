package navi.server.service.schedule.announcementSchedule;

import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.repository.schedule.UserScheduleSubclasses.AnnouncementScheduleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnouncementScheduleServiceImpl implements AnnouncementScheduleService {

    private final AnnouncementScheduleRepository announcementScheduleRepository;

    @Override
    public AnnouncementSchedule createAnnouncementSchedule(AnnouncementSchedule announcementSchedule) {
        return announcementScheduleRepository.save(announcementSchedule);
    }

    @Override
    public void deleteAnnouncementSchedule(String anId) {
        announcementScheduleRepository.delete(anId);
    }

    @Override
    public AnnouncementSchedule findAnnouncementSchedule(String anId) {
        return announcementScheduleRepository.findById(anId);
    }

}
