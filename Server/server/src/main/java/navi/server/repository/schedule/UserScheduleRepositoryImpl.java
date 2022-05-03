package navi.server.repository.schedule;

import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserScheduleRepositoryImpl implements UserScheduleRepository {

    private Map<Long,UserSchedule> store = new ConcurrentHashMap<>();
    private Long uniqueId = 0L; //임시

    @Override
    public UserSchedule save(UserSchedule userSchedule) {
        userSchedule.setId(uniqueId++);//임시: 별도 unique id 생성 로직 짜야함
        store.put(userSchedule.getId(),userSchedule);
        return userSchedule;
    }

    @Override
    public UserSchedule findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<UserSchedule> findByDate(String date) {
        return findAll().stream()
                .filter(m -> m.getDate().equals(date))
                .findFirst();
    }

    @Override
    public List<UserSchedule> findAll() {
        return new ArrayList<>(store.values()); //ArrayList로 조회 O(1)
    }


    @Override
    public UserSchedule update(String LoginId, UserSchedule updateParam) {
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
