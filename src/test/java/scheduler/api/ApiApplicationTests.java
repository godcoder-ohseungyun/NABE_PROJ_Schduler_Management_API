package scheduler.api;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {
    @Nested
    class ProblemTest {
        @Test
        void case1() {
            assertThat(1).isEqualTo(1);
        }
    }
}
