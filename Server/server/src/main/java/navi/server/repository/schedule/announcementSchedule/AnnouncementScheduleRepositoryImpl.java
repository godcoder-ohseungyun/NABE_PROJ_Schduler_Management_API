package navi.server.repository.schedule.announcementSchedule;


import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class AnnouncementScheduleRepositoryImpl implements AnnouncementScheduleRepository {
    private Map<String, AnnouncementSchedule> store = new ConcurrentHashMap<>();


    /**
     *
     * @param announcementSchedule
     * @return
     */
    @Override
    public AnnouncementSchedule save(AnnouncementSchedule announcementSchedule) {

        store.put(announcementSchedule.getAnno_id(), announcementSchedule);

        return announcementSchedule;
    }

    @Override
    public AnnouncementSchedule findById(String anId) {
        return store.get(anId);
    }

    @Override
    public void delete(String anId) {
        store.remove(anId);
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
