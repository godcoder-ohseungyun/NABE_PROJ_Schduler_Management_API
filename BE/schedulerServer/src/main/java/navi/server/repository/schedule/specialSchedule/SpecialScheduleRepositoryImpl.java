package navi.server.repository.schedule.specialSchedule;

import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;
import org.springframework.stereotype.Repository;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class SpecialScheduleRepositoryImpl implements SpecialScheduleRepository {
    private Map<String, SpecialSchedule> store = new ConcurrentHashMap<>();

    @Override
    public SpecialSchedule save(SpecialSchedule specialSchedule) {

        store.put(specialSchedule.getName()+specialSchedule.getTest_date(),specialSchedule);

        return specialSchedule;
    }

    @Override
    public SpecialSchedule findById(String id) {
        return store.get(id);
    }

    @Override
    public void delete(String id) {
        store.remove(id);
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
