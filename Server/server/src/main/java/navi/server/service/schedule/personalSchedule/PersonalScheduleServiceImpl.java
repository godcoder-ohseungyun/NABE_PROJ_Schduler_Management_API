package navi.server.service.schedule.personalSchedule;

import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;
import navi.server.repository.schedule.UserScheduleSubclasses.PersonalScheduleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonalScheduleServiceImpl implements PersonalScheduleService{


    private final PersonalScheduleRepository personalScheduleRepository;

    @Override
    public PersonalSchedule createPersonalSchedule(PersonalSchedule personalSchedule) {
        return personalScheduleRepository.save(personalSchedule);
    }

    @Override
    public void deletePersonalSchedule(Long id) {
        personalScheduleRepository.delete(id);
    }

    @Override
    public void updatePersonalSchedule(Long id, PersonalSchedule personalSchedule) {
        personalScheduleRepository.update(id,personalSchedule);
    }
}
