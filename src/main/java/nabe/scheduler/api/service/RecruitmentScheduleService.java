package nabe.scheduler.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nabe.scheduler.api.domain.RecruitmentSchedule;
import nabe.scheduler.api.repository.RecruitmentScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecruitmentScheduleService {

    private final RecruitmentScheduleRepository recruitmentScheduleRepository;

    //TODO : refactor - separate this method to create & find
    @Transactional
    public RecruitmentSchedule getExistOrCreation(RecruitmentSchedule recruitmentSchedule){
        RecruitmentSchedule find = recruitmentScheduleRepository.findOneById(recruitmentSchedule.getId());
        if(find==null) return recruitmentScheduleRepository.save(recruitmentSchedule);
        return find;
    }
}
