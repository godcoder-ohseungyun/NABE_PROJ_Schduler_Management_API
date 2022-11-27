package nabe.scheduler.api.service;

import nabe.scheduler.api.domain.RecruitmentSchedule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
@Transactional
class RecruitmentScheduleServiceTest {

    @Autowired
    RecruitmentScheduleService recruitmentScheduleService;

    @Autowired
    EntityManager em;

    @DisplayName("일정을 조회하 되, 없는 경우 생성해서 조회함")
    @Test
    public void testGetRecruitmentSchedule(){
        RecruitmentSchedule createdRecruitmentSchedule = RecruitmentSchedule.createRecruitmentSchedule(1l
                , "제목"
                , "https://www.test.com"
                , "20221020"
                , "20221024");

        assertThat(recruitmentScheduleService.getExistOrCreation(createdRecruitmentSchedule)).isEqualTo(createdRecruitmentSchedule);
    }

}