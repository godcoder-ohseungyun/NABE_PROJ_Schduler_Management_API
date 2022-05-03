package navi.server.repository.schedule.UserScheduleSubclasses;

import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class PersonalScheduleRepositoryImpl implements  PersonalScheduleRepository{
    private Map<Long, PersonalSchedule> store = new ConcurrentHashMap<>();
    private Long uniqueId = 0L; //임시

    @Override
    public PersonalSchedule save(PersonalSchedule personalSchedule) {
        personalSchedule.setId(uniqueId++);//임시: 별도 unique id 생성 로직 짜야함
        store.put(personalSchedule.getId(),personalSchedule);
        return personalSchedule;
    }

    @Override
    public PersonalSchedule findById(Long id) {
        return store.get(id);
    }


    @Override
    public PersonalSchedule update(String LoginId, PersonalSchedule updateParam) {
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
