package scheduler.api;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Test;
import scheduler.api.controller.PersonalScheduler;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PersonalScheduler.class)
public class DummyTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void test(){
        throw new RuntimeException();
    }
}
