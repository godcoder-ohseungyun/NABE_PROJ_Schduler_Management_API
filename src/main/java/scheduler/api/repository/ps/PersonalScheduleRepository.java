package scheduler.api.repository.ps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import scheduler.api.domain.ps.PersonalSchedule;
import scheduler.api.dto.an.ReadingAnnouncementSubscriptionDto;
import scheduler.api.dto.ps.ReadingPersonalScheduleDto;
import scheduler.api.dto.ps.UpdatingPersonalScheduleContentsDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Slf4j
public class PersonalScheduleRepository {
    @PersistenceContext
    private EntityManager em;

    //update 역할도 같이 하나? merge?
    public void save(PersonalSchedule personalSchedule) {

        em.persist(personalSchedule);


    }

    public void deleteThese(List<Long> personalScheduleIdList) {

        //log.info(ids.toString());
        em.createQuery("delete from PersonalSchedule p where p.id in :idList").setParameter("idList", personalScheduleIdList).executeUpdate(); //delete는 excuteUpdate()없이 쿼리 안남감 왜?

    }

    //NoResultException 위험 있음
    public void update(UpdatingPersonalScheduleContentsDto updatingPsContentsDto) {
        PersonalSchedule findPersonalSchedule = em.createQuery("select ps from PersonalSchedule  ps where ps.id = :id", PersonalSchedule.class)
                .setParameter("id", updatingPsContentsDto.getPersonalScheduleId()).getSingleResult();

        findPersonalSchedule.updateThisContents(updatingPsContentsDto.getTitle(), updatingPsContentsDto.getBody());

    }

    public List<ReadingPersonalScheduleDto> findAllByMemberId(Long memberId) {
        return em.createQuery("select new scheduler.api.dto.ps.ReadingPersonalScheduleDto(" +
                        "ps.id," +
                        "ps.title," +
                        "ps.body," +
                        "ps.startTime," +
                        "ps.endTime," +
                        "ps.startDate) from PersonalSchedule ps where ps.memberId = :id", ReadingPersonalScheduleDto.class)
                .setParameter("id", memberId).getResultList();

    }


}
