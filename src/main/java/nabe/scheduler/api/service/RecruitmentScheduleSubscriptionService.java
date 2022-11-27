package nabe.scheduler.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nabe.scheduler.api.domain.RecruitmentSchedule;
import nabe.scheduler.api.domain.RecruitmentScheduleSubscription;
import nabe.scheduler.api.dto.ReadingRecruitmentScheduleSubscriptionDto;
import nabe.scheduler.api.exception.definedException.DuplicateDataException;
import nabe.scheduler.api.repository.RecruitmentScheduleSubscriptionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecruitmentScheduleSubscriptionService {

    private static final String DUPLICATE_SUBSCRIBE_REQUEST_ERROR_MSG = "이미 구독한 채용 일정 입니다.";
    private final RecruitmentScheduleSubscriptionRepository recruitmentScheduleSubscriptionRepository;
    private final RecruitmentScheduleService recruitmentScheduleService;

    @Transactional
    public void subscribe(Long memberId, RecruitmentSchedule recruitmentSchedule) {

        RecruitmentSchedule readRecruitmentSchedule = recruitmentScheduleService.getExistOrCreation(recruitmentSchedule);

        if (recruitmentScheduleSubscriptionRepository.isNotMapped(memberId, readRecruitmentSchedule.getId())){

            RecruitmentScheduleSubscription createdRecruitmentScheduleSubscription = RecruitmentScheduleSubscription
                    .createRecruitmentScheduleSubscription(readRecruitmentSchedule, memberId);

            recruitmentScheduleSubscriptionRepository.save(createdRecruitmentScheduleSubscription);

            return;
        }

        throw new DuplicateDataException(DUPLICATE_SUBSCRIBE_REQUEST_ERROR_MSG, HttpStatus.CONFLICT);

    }

    @Transactional
    public void cancelSubscribe(List<Long> recruitmentScheduleSubscriptionIds) {
        recruitmentScheduleSubscriptionRepository.deleteThese(recruitmentScheduleSubscriptionIds);
    }

    @Transactional
    public List<ReadingRecruitmentScheduleSubscriptionDto> findAllByMemberId(Long memberId) {
        return recruitmentScheduleSubscriptionRepository.findAllByMemberId(memberId);
    }


}
