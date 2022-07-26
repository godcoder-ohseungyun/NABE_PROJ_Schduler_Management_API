package scheduler.api.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import scheduler.api.domain.PersonalSchedule;

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
    
    //쿼리문 IN으로 바꾸면 1번의 쿼리로 처리 가능
    public void delete(Long psId){
        em.createQuery("delete from personal_schedule ps where ps.id = :id")
                    .setParameter("id", psId);
    }

    public void update(Long psId,String title,String body){
        PersonalSchedule findPersonalSchedule = em.createQuery("select * form personal_schedule ps where ps.id = :id", PersonalSchedule.class)
                .setParameter("id",psId).getSingleResult();

        findPersonalSchedule.updateThisContents(title, body);
    }

    public List<PersonalSchedule> findAll(Long memberId){
        return em.createQuery("select * form personal_schedule ps where ps.member_id = :id", PersonalSchedule.class)
                .setParameter("id",memberId).getResultList();
    }


}
