package navi.server.repository.schedule.UserScheduleSubclasses;


import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class AnnouncementScheduleRepositoryImpl implements AnnouncementScheduleRepository{
    private Map<Long, AnnouncementSchedule> store = new ConcurrentHashMap<>();
    private Long uniqueId = 0L; //임시

    @Override
    public AnnouncementSchedule save(AnnouncementSchedule announcementSchedule) {
        announcementSchedule.setId(uniqueId++);//임시: 별도 unique id 생성 로직 짜야함
        store.put(announcementSchedule.getId(),announcementSchedule);
        return announcementSchedule;
    }

    @Override
    public AnnouncementSchedule findById(Long id) {
        return store.get(id);
    }


    @Override
    public AnnouncementSchedule update(String LoginId, AnnouncementSchedule updateParam) {
        return null;
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
