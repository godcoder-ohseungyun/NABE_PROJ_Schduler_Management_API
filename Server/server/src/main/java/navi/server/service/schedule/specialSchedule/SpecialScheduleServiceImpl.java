package navi.server.service.schedule.specialSchedule;

import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;
import navi.server.repository.schedule.specialSchedule.SpecialScheduleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialScheduleServiceImpl implements  SpecialScheduleService{

    private final SpecialScheduleRepository specialScheduleRepository;

    @Override
    public SpecialSchedule createSpecialSchedule(SpecialSchedule specialSchedule) {
        return specialScheduleRepository.save(specialSchedule);
    }

    @Override
    public boolean isIn(String spId){
        if(findSpecialSchedule(spId)==null) return false;

        return true;
    }

    @Override
    public void deleteSpecialSchedule(String spId) {
        specialScheduleRepository.delete(spId);
    }

    @Override
    public SpecialSchedule findSpecialSchedule(String spId) {
        return specialScheduleRepository.findById(spId);
    }
}
