package nabe.scheduler.api.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nabe.scheduler.api.domain.PersonalSchedule;
import nabe.scheduler.api.dto.ReadingPersonalScheduleDto;
import nabe.scheduler.api.dto.UpdatingPersonalScheduleContentsDto;
import nabe.scheduler.api.repository.PersonalScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonalScheduleService {

    private final PersonalScheduleRepository personalScheduleRepository;

    @Transactional
    public Long save(PersonalSchedule personalSchedule){
        personalScheduleRepository.save(personalSchedule);
        return personalSchedule.getId();
    }

    @Transactional
    public void deleteThese(List<Long> personalScheduleIdList){
        personalScheduleRepository.deleteThese(personalScheduleIdList);
    }

    @Transactional
    public List<ReadingPersonalScheduleDto> findAllByMemberId(Long memberId){
        return personalScheduleRepository.findAllByMemberId(memberId);
    }

    @Transactional
    public ReadingPersonalScheduleDto findByPersonalScheduleId(Long personalScheduleId){
        return personalScheduleRepository.findByPersonalScheduleId(personalScheduleId);
    }

    @Transactional
    public void update(UpdatingPersonalScheduleContentsDto updatingPsContentsDto){
        personalScheduleRepository.update(updatingPsContentsDto);
    }

}
