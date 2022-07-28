package scheduler.api.repository.ps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import scheduler.api.domain.ps.PersonalSchedule;
import scheduler.api.dto.ps.UpdatingPsContentsDto;

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
    public void save(PersonalSchedule ps){
        if(ps.getId() == null){
            em.persist(ps);
        }else{
            em.merge(ps);
        }

        
    }

    public void delete(List<Long> ids){

        //log.info(ids.toString());
        em.createQuery("delete from PersonalSchedule p where p.id in :ids").setParameter("ids",ids).executeUpdate(); //delete는 excuteUpdate()없이 쿼리 안남감 왜?

    }

    public void update(UpdatingPsContentsDto updatingPsContentsDto){
        PersonalSchedule findPersonalSchedule = em.createQuery("select ps from PersonalSchedule  ps where ps.id = :id", PersonalSchedule.class)
                .setParameter("id",updatingPsContentsDto.getPsId()).getSingleResult();

        findPersonalSchedule.updateThisContents(updatingPsContentsDto.getTitle(), updatingPsContentsDto.getBody());

    }

    public List<PersonalSchedule> findAll(Long memberId){
        return em.createQuery("select ps from PersonalSchedule ps where ps.memberId = :id", PersonalSchedule.class)
                .setParameter("id",memberId).getResultList();

    }


}
