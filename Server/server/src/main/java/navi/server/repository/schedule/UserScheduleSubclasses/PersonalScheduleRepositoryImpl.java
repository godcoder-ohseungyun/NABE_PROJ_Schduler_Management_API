package navi.server.repository.schedule.UserScheduleSubclasses;

import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;
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
    public PersonalSchedule update(Long id, PersonalSchedule update) {

        PersonalSchedule find = findById(id);

        if(find != null){
            find.setS_time(update.getS_time());
            find.setE_time(update.getE_time());
            find.setObservation(update.isObservation());
            find.setDetail(update.getDetail());
        }


        return find;
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
