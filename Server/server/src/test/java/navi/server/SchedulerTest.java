package navi.server;

import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.enumType.CertificationScheduleType;
import navi.server.domain.user.User;
import navi.server.repository.schedule.UserScheduleRepository;
import navi.server.repository.schedule.UserScheduleRepositoryImpl;
import navi.server.repository.user.UserRepository;
import navi.server.repository.user.UserRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 이렇게 만들면 안됨 전면 수정해야함, 확인은 되었으니 일단은 ok
 */

class SchedulerTest {

    private UserRepository userRepository = new UserRepositoryImpl();
    private UserScheduleRepository userScheduleRepository = new UserScheduleRepositoryImpl();


    private User firstUser = userRepository.save(new User("비밀번호", "tv0602@naver.com", "오승윤"));

    @AfterEach
    void afterEach() {
        userRepository.clearStore();
        userScheduleRepository.clearStore();
    }

    /**
     * [개인일정 추가 테스트]
     * 일정객체가 없는경우
     * 1.일정객체를 만듬
     * 2.만들어진 일정 객체에 개인일정을 추가
     * 3.인증된 사용자 객체에 해당 일정 객체 추가
     */
    //@Test

}