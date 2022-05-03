package navi.server.repository.schedule.UserScheduleSubclasses;

import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;
import org.springframework.stereotype.Repository;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class SpecialScheduleRepositoryImpl implements SpecialScheduleRepository {
    private Map<Long, SpecialSchedule> store = new ConcurrentHashMap<>();
    private Long uniqueId = 0L; //임시

    @Override
    public SpecialSchedule save(SpecialSchedule specialSchedule) {
        specialSchedule.setId(uniqueId++);//임시: 별도 unique id 생성 로직 짜야함
        store.put(specialSchedule.getId(),specialSchedule);
        return specialSchedule;
    }

    @Override
    public SpecialSchedule findById(Long id) {
        return store.get(id);
    }


    @Override
    public SpecialSchedule update(String LoginId, SpecialSchedule updateParam) {
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
