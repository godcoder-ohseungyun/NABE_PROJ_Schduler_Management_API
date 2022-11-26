package nabe.scheduler.api.service;

import nabe.scheduler.api.domain.PersonalSchedule;
import nabe.scheduler.api.dto.UpdatingPersonalScheduleContentsDto;
import nabe.scheduler.api.repository.PersonalScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
@Transactional
class PersonalScheduleServiceTest {

    @Autowired
    PersonalScheduleService personalScheduleService;

    @Autowired
    EntityManager em;

    private Long id_1;
    private Long id_2;


    @BeforeEach
    public void setUp(){
        PersonalSchedule createdPersonalSchedule1 = PersonalSchedule.createPersonalSchedule(
                "제목"
                ,"내용"
                ,"1020"
                ,"1200"
                ,"20220102"
                ,1l
        );

        PersonalSchedule createdPersonalSchedule2 = PersonalSchedule.createPersonalSchedule(
                "제목"
                ,"내용"
                ,"1020"
                ,"1200"
                ,"20220102"
                ,1l
        );

        id_1 = personalScheduleService.save(createdPersonalSchedule1);
        id_2 = personalScheduleService.save(createdPersonalSchedule2);

    }

    @Test
    public void testDeleteThese(){

        personalScheduleService.deleteThese(List.of(id_1,id_2));

        assertThat(personalScheduleService.findAllByMemberId(1l).size()).isEqualTo(0);
    }

    @Test
    public void testFindAllByMemberId(){
        assertThat(personalScheduleService.findAllByMemberId(1l).size()).isEqualTo(2);
    }

    @Test
    public void testUpdateContents(){
        personalScheduleService.update(new UpdatingPersonalScheduleContentsDto(
                id_1
                ,"제목_변경"
                ,"내용_변경"));

        assertThat(personalScheduleService.findByPersonalScheduleId(id_1).getTitle()).isEqualTo("제목_변경");
        assertThat(personalScheduleService.findByPersonalScheduleId(id_1).getBody()).isEqualTo("내용_변경");
    }

}