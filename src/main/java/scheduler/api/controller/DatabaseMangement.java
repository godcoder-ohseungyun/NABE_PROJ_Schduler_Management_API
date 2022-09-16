package scheduler.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/api/admin/database")
@RequiredArgsConstructor
public class DatabaseMangement {

    @PersistenceContext
    private EntityManager em;

    /**
     * 임시 방편 코드 : 테이블의 변경이 있을 때마다 매번 sql 파일을 수정하는 것은 비효율적 -> 수정 예정
     * https://steady-coding.tistory.com/579
     *
     */
    @DeleteMapping("/tables")
    @Transactional
    public void deleteAllTableData(){
        String[] tableNames = new String[3];
        tableNames[0] = "announcement_schedule";
        tableNames[1] = "announcement_subscription";
        tableNames[2] = "personal_schedule";

        //foreign key 걸린 테이블 강제 삭제
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        for(String tableName : tableNames){
            em.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        }
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }
}
