package navi.server.repository.schedule.userSchedule;


import navi.server.domain.schedule.UserSchedule;

import java.util.List;
import java.util.Optional;

public interface UserScheduleRepository {
    UserSchedule save(UserSchedule userSchedule);

    UserSchedule findById(Long id);

    Optional<UserSchedule> findByDate(String date);

    public List<UserSchedule> findAll();

    void delete(Long id);

    void clearStore();
}
