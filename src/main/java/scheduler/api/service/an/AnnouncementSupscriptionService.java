package scheduler.api.service.an;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scheduler.api.domain.an.AnnouncementSchedule;
import scheduler.api.domain.an.AnnouncementSubscription;
import scheduler.api.dto.an.ReadingAnnouncementSubscriptionDto;
import scheduler.api.exception.exceptionDomain.DuplicateDataException;
import scheduler.api.repository.an.AnnouncementSubscriptionRepository;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
public class AnnouncementSupscriptionService {

    @Autowired
    private AnnouncementSubscriptionRepository announcementSubscriptionRepository;
    @Autowired
    private AnnouncementScheduleService announcementScheduleService;

    @Transactional
    public void subscribe(Long memberId, AnnouncementSchedule announcementSchedule) throws DuplicateDataException {

        //구독하고자 하는 공고
        AnnouncementSchedule getAnnouncementSchedule = announcementScheduleService.getFindOrCreation(announcementSchedule); //무조건 영속상태로 반환함


        if (announcementSubscriptionRepository.isNotMapped(memberId, getAnnouncementSchedule.getId())){

            AnnouncementSubscription newAnnouncementSubscription = AnnouncementSubscription.createAnnouncementSubscription(getAnnouncementSchedule, memberId);
            announcementSubscriptionRepository.save(newAnnouncementSubscription);

        } else {
            throw new DuplicateDataException("이미 구독한 공고 입니다.", HttpStatus.CONFLICT);
        }

    }

    @Transactional
    public void cancelSubscribe(List<Long> announcementSubscriptionIdList) {
        announcementSubscriptionRepository.deleteThese(announcementSubscriptionIdList);
    }


    @Transactional
    public List<ReadingAnnouncementSubscriptionDto> findAllByMemberId(Long memberId) {
        return announcementSubscriptionRepository.findAllByMemberId(memberId);
    }


}