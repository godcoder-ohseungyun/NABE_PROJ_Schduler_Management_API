package scheduler.api.repository.an;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import scheduler.api.domain.an.AnnouncementSubscription;
import scheduler.api.dto.an.ReadingAnnouncementSubscriptionDto;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * 추가: 단일 추가
 * 삭제: 단일 삭제
 */
@Repository
@Slf4j
@RequiredArgsConstructor
public class AnnouncementSubscriptionRepository {

    private final EntityManager em;


    public void save(AnnouncementSubscription announcementSubscription) {
        if (announcementSubscription.getId() == null) {
            em.persist(announcementSubscription);
        } else {
            em.merge(announcementSubscription);
        }
    }

    public Boolean isNotMapped(Long memberId, Long announcementScheduleId) {
        try {
            em.createQuery("select a from AnnouncementSubscription a where a.announcementSchedule.id = :announcementScheduleId and a.memberId = :memberId", AnnouncementSubscription.class)
                    .setParameter("announcementScheduleId", announcementScheduleId)
                    .setParameter("memberId", memberId)
                    .getSingleResult();

            return false;

        } catch (NoResultException e) {
            return true;
        }
    }

    public List<ReadingAnnouncementSubscriptionDto> findAllByMemberId(Long memberId) {
        return em.createQuery("select new scheduler.api.dto.an.ReadingAnnouncementSubscriptionDto(a.id," +
                        "a.announcementSchedule.title," +
                        "a.announcementSchedule.originalUrl," +
                        "a.announcementSchedule.startDate," +
                        "a.announcementSchedule.endDate) from AnnouncementSubscription a where a.memberId = :id", ReadingAnnouncementSubscriptionDto.class)
                .setParameter("id", memberId).getResultList();

    }


    public void deleteThese(List<Long> announcementSubscriptionIdList) {
        em.createQuery("delete from AnnouncementSubscription a where a.id in :idList").setParameter("idList", announcementSubscriptionIdList).executeUpdate(); //delete는 excuteUpdate()없이 쿼리 안남감 왜?
    }
}
