package scheduler.api.repository.an;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import scheduler.api.domain.an.AnnouncementSchedule;

import javax.persistence.EntityManager;

/**
 * 추가: 단일 추가
 * 삭제: 단일 삭제 //최적화 부분에서 필요시 생성 예정
 * 수정: 단일 수정 //수정이 발생하는 일이 있는지 확인 후 생성 예정
 */
@Repository
@RequiredArgsConstructor
public class AnnouncementScheduleRepository {

    private final EntityManager em;


    public AnnouncementSchedule save(AnnouncementSchedule announcementSchedule) {
        if (announcementSchedule.getId() == null) { //이 구문 필요없음, 상위에서 있는지 없는지 확인했잖아
            em.persist(announcementSchedule);
        } else {
            em.merge(announcementSchedule); //id는 입력으로 받았으니 항상 이 구문에 걸려서 merge를 진행했을텐데,  null위험도 있고 반환객체를 쓰지않으면 준영속상태 유지함
        }

        return announcementSchedule; //파라미터로 넘어온것은 준영속상태로 남는다. 머지의 결과값으로 가야함, 근데 머지를 쓰는게 올바르지 않음
    }

    public AnnouncementSchedule findOne(Long anId) {
        return em.find(AnnouncementSchedule.class, anId);
    }

}
