package navi.server.service.schedule.userSchedule;


import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.UserSchedule;
import navi.server.repository.schedule.userSchedule.UserScheduleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserScheduleServiceImpl implements UserScheduleService {

    private final UserScheduleRepository userScheduleRepository;

    @Override
    public UserSchedule createUserSchedule(UserSchedule userSchedule){
        return userScheduleRepository.save(userSchedule);
    }

    @Override
    public void deleteUserSchedule(Long id){
        userScheduleRepository.delete(id);
    }




}
