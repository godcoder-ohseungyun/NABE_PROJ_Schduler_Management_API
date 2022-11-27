package nabe.scheduler.api.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/api/admin/database")
@RequiredArgsConstructor
public class DatabaseMangementController {
    private final EntityManager em;

    //TODO: it is inefficient to modify the sql file every time there is a table change. so,to be modified (https://steady-coding.tistory.com/579)
    @DeleteMapping("/tables")
    @Transactional
    public void forceDeleteAllTableData(){
        String[] tableNames = new String[3];
        tableNames[0] = "recruitment_schedule";
        tableNames[1] = "recruitment_schedule_subscription";
        tableNames[2] = "personal_schedule";

        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        for(String tableName : tableNames){
            em.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        }
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }
}
