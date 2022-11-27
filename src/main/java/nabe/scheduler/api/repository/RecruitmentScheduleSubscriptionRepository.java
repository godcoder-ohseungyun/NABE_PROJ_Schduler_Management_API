package nabe.scheduler.api.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nabe.scheduler.api.domain.RecruitmentScheduleSubscription;
import nabe.scheduler.api.dto.ReadingRecruitmentScheduleSubscriptionDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class RecruitmentScheduleSubscriptionRepository {

    private final EntityManager em;


    public void save(RecruitmentScheduleSubscription recruitmentScheduleSubscription) {
        if (recruitmentScheduleSubscription.getId() == null) {
            em.persist(recruitmentScheduleSubscription);
        } else {
            em.merge(recruitmentScheduleSubscription);
        }
    }

    public Boolean isNotMapped(Long memberId, Long recruitmentScheduleId) {
        try {
            em.createQuery("select a from RecruitmentScheduleSubscription a " +
                            "where a.recruitmentSchedule.id = :recruitmentScheduleId " +
                            "and a.memberId = :memberId", RecruitmentScheduleSubscription.class)
                    .setParameter("recruitmentScheduleId", recruitmentScheduleId)
                    .setParameter("memberId", memberId)
                    .getSingleResult();
            return false;
        } catch (NoResultException e) {
            return true;
        }
    }

    public List<ReadingRecruitmentScheduleSubscriptionDto> findAllByMemberId(Long memberId) {
        return em.createQuery("select new nabe.scheduler.api.dto.ReadingRecruitmentScheduleSubscriptionDto(a.id," +
                        "a.recruitmentSchedule.title," +
                        "a.recruitmentSchedule.originalUrl," +
                        "a.recruitmentSchedule.startDate," +
                        "a.recruitmentSchedule.endDate) from RecruitmentScheduleSubscription a where a.memberId = :id", ReadingRecruitmentScheduleSubscriptionDto.class)
                .setParameter("id", memberId).getResultList();

    }


    public void deleteThese(List<Long> recruitmentScheduleSubscriptionIds) {
        em.createQuery("delete from RecruitmentScheduleSubscription a " +
                "where a.id in :idList")
                .setParameter("idList", recruitmentScheduleSubscriptionIds).executeUpdate();
    }
}
