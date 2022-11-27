package nabe.scheduler.api.service;

import nabe.scheduler.api.domain.RecruitmentSchedule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
@Transactional
class RecruitmentScheduleSubscriptionServiceTest {

    @Autowired
    RecruitmentScheduleSubscriptionService recruitmentScheduleSubscriptionService;

    @Autowired
    EntityManager em;

    @Test
    public void testSubscribeRecruitmentSchedule() {
        RecruitmentSchedule createdRecruitmentSchedule = RecruitmentSchedule.createRecruitmentSchedule(1l
                , "제목"
                , "https://www.test.com"
                , "20221020"
                , "20221024");

        recruitmentScheduleSubscriptionService.subscribe(1l, createdRecruitmentSchedule);

        assertThat(recruitmentScheduleSubscriptionService.findAllByMemberId(1l).size()).isEqualTo(1);
    }

    @Test
    public void testCancelSubscribeRecruitmentSchedule() {
        RecruitmentSchedule createdRecruitmentSchedule = RecruitmentSchedule.createRecruitmentSchedule(1l
                , "제목"
                , "https://www.test.com"
                , "20221020"
                , "20221024");

        recruitmentScheduleSubscriptionService.subscribe(1l, createdRecruitmentSchedule);

        Long subscribedRecruitmentScheduleSubscriptionId = recruitmentScheduleSubscriptionService
                .findAllByMemberId(1l).get(0)
                .getRecruitmentScheduleSubscriptionId();

        recruitmentScheduleSubscriptionService.cancelSubscribe(List.of(subscribedRecruitmentScheduleSubscriptionId));

        assertThat(recruitmentScheduleSubscriptionService.findAllByMemberId(1l).size()).isEqualTo(0);
    }
}