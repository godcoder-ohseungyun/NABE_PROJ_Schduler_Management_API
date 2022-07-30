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

        em.persist(announcementSchedule);

        return announcementSchedule;
    }

    public AnnouncementSchedule findOne(Long anId) {
        return em.find(AnnouncementSchedule.class, anId);
    }

}
