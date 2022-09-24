package scheduler.api.service.ps;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scheduler.api.domain.ps.PersonalSchedule;
import scheduler.api.dto.ps.ReadingPersonalScheduleDto;
import scheduler.api.dto.ps.UpdatingPersonalScheduleContentsDto;
import scheduler.api.repository.ps.PersonalScheduleRepository;

import java.util.List;


@Service
@Slf4j
@Transactional(readOnly = true)
public class PersonalScheduleService {

    @Autowired
    PersonalScheduleRepository personalScheduleRepository;

    @Transactional
    public Long save(PersonalSchedule personalSchedule){
        personalScheduleRepository.save(personalSchedule);
        return personalSchedule.getId();
    }

    @Transactional
    public void delete(List<Long> personalScheduleIdList){
        personalScheduleRepository.deleteThese(personalScheduleIdList);
    }

    @Transactional
    public List<ReadingPersonalScheduleDto> findAllByMemberId(Long memberId){
        return personalScheduleRepository.findAllByMemberId(memberId);
    }

    @Transactional
    public void update(UpdatingPersonalScheduleContentsDto updatingPsContentsDto){
        personalScheduleRepository.update(updatingPsContentsDto);
    }

}
