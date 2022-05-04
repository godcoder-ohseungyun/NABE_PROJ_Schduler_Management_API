package navi.server.service.schedule.specialSchedule;

import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;
import navi.server.repository.schedule.UserScheduleSubclasses.SpecialScheduleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialScheduleServiceImpl implements  SpecialScheduleService{

    private final SpecialScheduleRepository specialScheduleRepository;

    @Override
    public SpecialSchedule createSpecialScheduleService(SpecialSchedule specialSchedule) {
        return specialScheduleRepository.save(specialSchedule);
    }

    @Override
    public void deleteSpecialScheduleService(Long id) {
        specialScheduleRepository.delete(id);
    }
}
