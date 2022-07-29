package scheduler.api.repository.an;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import scheduler.api.domain.an.AnnouncementSubscription;
import scheduler.api.dto.an.ReadingAsubDto;

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

    public AnnouncementSubscription isMapped(Long memberId, Long anId) {
        try {
            return em.createQuery("select a from AnnouncementSubscription a where a.announcementSchedule.id = :anId and a.memberId = :memberId", AnnouncementSubscription.class)
                    .setParameter("anId", anId)
                    .setParameter("memberId", memberId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<ReadingAsubDto> findAll(Long memberId) {
        return em.createQuery("select new scheduler.api.dto.an.ReadingAsubDto(a.id," +
                        "a.announcementSchedule.title," +
                        "a.announcementSchedule.originalUrl," +
                        "a.announcementSchedule.startDate," +
                        "a.announcementSchedule.endDate) from AnnouncementSubscription a where a.memberId = :id", ReadingAsubDto.class)
                .setParameter("id", memberId).getResultList();

    }


    public void deleteThem(List<Long> asubIds) {
        em.createQuery("delete from AnnouncementSubscription a where a.id in :ids").setParameter("ids", asubIds).executeUpdate(); //delete는 excuteUpdate()없이 쿼리 안남감 왜?
    }
}
