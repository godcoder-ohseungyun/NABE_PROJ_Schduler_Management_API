package scheduler.api.service.ps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import scheduler.api.ApiApplication;
import scheduler.api.ApiApplicationTests;
import scheduler.api.domain.ps.PersonalSchedule;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonalScheduleServiceTest {

    @Autowired
    PersonalScheduleService personalScheduleService;


    @Test
    public void test() {
        assertEquals(1,2);
    }
}