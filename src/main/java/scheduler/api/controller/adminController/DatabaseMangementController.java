package scheduler.api.controller.adminController;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @detail
 * - admin 전용 DB 관리 컨트롤러로 초기화 등 민감한 동작을 지원합니다.
 * - TODO: 어드민만 접근 가능하게 제한하는 시스템 추가
 */
@RestController
@RequestMapping("/api/admin/database")
@RequiredArgsConstructor
public class DatabaseMangementController {

    @PersistenceContext
    private EntityManager em;

    /**
     * TODO: 임시 방편 코드 임, 테이블의 변경이 있을 때마다 매번 sql 파일을 수정하는 것은 비효율적 -> 수정 예정(https://steady-coding.tistory.com/579)
     */
    @DeleteMapping("/tables")
    @Transactional
    public void deleteAllTableData(){
        String[] tableNames = new String[3];
        tableNames[0] = "announcement_schedule";
        tableNames[1] = "announcement_subscription";
        tableNames[2] = "personal_schedule";

        //주의: FK 걸린 테이블도 강제 삭제
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        for(String tableName : tableNames){
            em.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        }
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }
}
