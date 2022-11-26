package nabe.scheduler.api.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nabe.scheduler.api.domain.PersonalSchedule;
import nabe.scheduler.api.dto.ReadingPersonalScheduleDto;
import nabe.scheduler.api.dto.UpdatingPersonalScheduleContentsDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@RequiredArgsConstructor
@Slf4j
public class PersonalScheduleRepository {
    private final EntityManager em;

    public void save(PersonalSchedule personalSchedule) {
        em.persist(personalSchedule);
    }

    public void deleteThese(List<Long> personalScheduleIds) {
        em.createQuery("delete from PersonalSchedule p where p.id in :ids")
                .setParameter("ids", personalScheduleIds)
                .executeUpdate();

    }

    public void update(UpdatingPersonalScheduleContentsDto updatingPsContentsDto) {
        PersonalSchedule findPersonalSchedule = em.createQuery(
                "select ps from PersonalSchedule  ps where ps.id = :id"
                        , PersonalSchedule.class)
                .setParameter("id", updatingPsContentsDto.getPersonalScheduleId())
                .getSingleResult();

        findPersonalSchedule.updateThisContents(updatingPsContentsDto.getTitle(), updatingPsContentsDto.getBody());

    }

    public List<ReadingPersonalScheduleDto> findAllByMemberId(Long memberId) {
        return em.createQuery("select new nabe.scheduler.api.dto.ReadingPersonalScheduleDto(" +
                        "ps.id" +
                        ",ps.title" +
                        ",ps.body" +
                        ",ps.startTime" +
                        ",ps.endTime" +
                        ",ps.startDate) " +
                        "from PersonalSchedule ps where ps.memberId = :id"
                        , ReadingPersonalScheduleDto.class)
                .setParameter("id", memberId)
                .getResultList();

    }

    public ReadingPersonalScheduleDto findByPersonalScheduleId(Long personalScheduleId) {
        return em.createQuery("select new nabe.scheduler.api.dto.ReadingPersonalScheduleDto(" +
                                "ps.id" +
                                ",ps.title" +
                                ",ps.body" +
                                ",ps.startTime" +
                                ",ps.endTime" +
                                ",ps.startDate) " +
                                "from PersonalSchedule ps where ps.id = :id"
                        , ReadingPersonalScheduleDto.class)
                .setParameter("id", personalScheduleId)
                .getSingleResult();
    }


}
