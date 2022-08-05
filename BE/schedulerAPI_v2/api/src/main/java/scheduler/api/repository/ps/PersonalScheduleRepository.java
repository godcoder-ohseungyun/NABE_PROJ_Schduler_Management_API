package scheduler.api.repository.ps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import scheduler.api.domain.ps.PersonalSchedule;
import scheduler.api.dto.ps.UpdatingPersonalScheduleContentsDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 삽입 - 단일 삽입
 * 삭제 - 단일~여러개 삭제
 * 수정 - 단일 수정
 * 읽기 - 전체 조회 , 특정 조건 조회
 */
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

    public void update(UpdatingPersonalScheduleContentsDto updatingPsContentsDto) {
        PersonalSchedule findPersonalSchedule = em.createQuery("select ps from PersonalSchedule  ps where ps.id = :id", PersonalSchedule.class)
                .setParameter("id", updatingPsContentsDto.getPersonalScheduleId()).getSingleResult();

        findPersonalSchedule.updateThisContents(updatingPsContentsDto.getTitle(), updatingPsContentsDto.getBody());

    }

    public List<PersonalSchedule> findAllByMemberId(Long memberId) {
        return em.createQuery("select ps from PersonalSchedule ps where ps.memberId = :id", PersonalSchedule.class)
                .setParameter("id", memberId).getResultList();

    }


}
