package scheduler.api;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@Transactional
public class ApiApplicationTests {

	@Test
	public void test1() throws Exception {

		assertEquals(1,1);
	}

	@Test
	public void test2() throws Exception {

		assertEquals(1,2);
	}

}
