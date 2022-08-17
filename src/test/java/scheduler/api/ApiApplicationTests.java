package scheduler.api;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import scheduler.api.service.ps.PersonalScheduleServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ApiApplicationTests {

	@Test
	public void contextLoads() {
		assertEquals(1,2);
	}



}
