package nabe.scheduler.api.repository;


import lombok.RequiredArgsConstructor;
import nabe.scheduler.api.domain.RecruitmentSchedule;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
@RequiredArgsConstructor
public class RecruitmentScheduleRepository {

    private final EntityManager em;

    public RecruitmentSchedule save(RecruitmentSchedule recruitmentSchedule) {
        em.persist(recruitmentSchedule);
        return recruitmentSchedule;
    }

    public RecruitmentSchedule findOneById(Long recruitmentScheduleId) {
        return em.find(RecruitmentSchedule.class, recruitmentScheduleId);
    }

}
