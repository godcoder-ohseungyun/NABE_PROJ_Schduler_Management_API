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


    private User firstUser = userRepository.save(new User("비밀번호","tv0602@naver.com","오승윤"));

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
    @Test
    void createPS1() {
        //given


        String date = "2022-05-03";
        PersonalSchedule personalSchedule = new PersonalSchedule("08:30","09:30",false,"대웅이 생일");


        //when
        User findUser = userRepository.findById(0l);

        if(findUser.getUserSchedules().get(date)==null){
            UserSchedule createUserSchedule = userScheduleRepository.save(new UserSchedule(date));
            createUserSchedule.getPersonalSchedules().add(personalSchedule);
            findUser.getUserSchedules().put(date,createUserSchedule);
        }

        //then
        assertThat(firstUser).isEqualTo(findUser);
        assertThat(firstUser.getUserSchedules().get("2022-05-03").getPersonalSchedules().size()).isEqualTo(1); //일정 객체추가후 해당 객체에 개인 일정도 추가완료



    }

    /**
     * [개인일정 추가 테스트]
     * 일정객체가 이미 있는경우
     * 1.존재하는 일정 객체에 개인일정을 추가
     */
    @Test
    void createPS2() {

        //given


        String date = "2022-05-03";
        PersonalSchedule personalSchedule = new PersonalSchedule("08:30","09:30",false,"대웅이 생일");
        PersonalSchedule personalSchedule2 = new PersonalSchedule("08:30","09:30",false,"시훈이 생일");
        //when
        User findUser = userRepository.findById(0l);

        UserSchedule createUserSchedule = userScheduleRepository.save(new UserSchedule(date));
        createUserSchedule.getPersonalSchedules().add(personalSchedule);
        findUser.getUserSchedules().put(date,createUserSchedule);
        
        if(findUser.getUserSchedules().get(date)!=null){
            UserSchedule findUserSchedule = findUser.getUserSchedules().get(date);
            findUserSchedule.getPersonalSchedules().add(personalSchedule2);
        }

        //then
        assertThat(firstUser).isEqualTo(findUser);
        assertThat(firstUser.getUserSchedules().get("2022-05-03").getPersonalSchedules().size()).isEqualTo(2);
    }


    /**
     * [공고일정 추가 테스트]
     * 일정객체가 없는경우
     * 1.일정객체를 만듬
     * 2.만들어진 일정 객체에 공고일정 추가
     * 3.인증된 사용자 객체에 해당 일정 객체 추가
     */
    @Test
    void createAN1() {
        //given


        String date = "2022-05-03";
        AnnouncementSchedule announcementSchedule = new AnnouncementSchedule("203911","www.test","삼성전자","백엔드 직군");


        //when
        User findUser = userRepository.findById(0l);

        if(findUser.getUserSchedules().get(date)==null){
            UserSchedule createUserSchedule = userScheduleRepository.save(new UserSchedule(date));
            createUserSchedule.getAnnouncementSchedules().add(announcementSchedule);
            findUser.getUserSchedules().put(date,createUserSchedule);
        }

        //then
        assertThat(firstUser).isEqualTo(findUser);
        assertThat(firstUser.getUserSchedules().get("2022-05-03").getAnnouncementSchedules().size()).isEqualTo(1); //일정 객체추가후 해당 객체에 개인 일정도 추가완료

    }


    /**
     * [공고일정 추가 테스트]
     * 일정객체가 이미 있는경우
     * 1.존재하는 일정 객체에 공고일정 추가
     */
    @Test
    void createAN2() {
        //given


        String date = "2022-05-03";
        AnnouncementSchedule announcementSchedule = new AnnouncementSchedule("203911","www.test","삼성전자","백엔드 직군");
        AnnouncementSchedule announcementSchedule2 = new AnnouncementSchedule("2039121","www.test","카카오","프론트 직군");
        //when
        User findUser = userRepository.findById(0l);

        UserSchedule createUserSchedule = userScheduleRepository.save(new UserSchedule(date));
        createUserSchedule.getAnnouncementSchedules().add(announcementSchedule);
        findUser.getUserSchedules().put(date,createUserSchedule);

        if(findUser.getUserSchedules().get(date)!=null){
            UserSchedule findUserSchedule = findUser.getUserSchedules().get(date);
            findUserSchedule.getAnnouncementSchedules().add(announcementSchedule2);
        }

        //then
        assertThat(firstUser).isEqualTo(findUser);
        assertThat(firstUser.getUserSchedules().get("2022-05-03").getAnnouncementSchedules().size()).isEqualTo(2);



    }


    /**
     * [특별일정 추가 테스트]
     * 일정객체가 없는경우
     * 1.일정객체를 만듬
     * 2.만들어진 일정 객체에 특별일정 추가
     * 3.인증된 사용자 객체에 해당 일정 객체 추가
     */
    @Test
    void createSP1() {
        //given


        String date = "2022-05-03";
        SpecialSchedule specialSchedule = new SpecialSchedule("Opic", CertificationScheduleType.APPLY);


        //when
        User findUser = userRepository.findById(0l);

        if(findUser.getUserSchedules().get(date)==null){
            UserSchedule createUserSchedule = userScheduleRepository.save(new UserSchedule(date));
            createUserSchedule.getSpecialSchedules().add(specialSchedule);
            findUser.getUserSchedules().put(date,createUserSchedule);
        }

        //then
        assertThat(firstUser).isEqualTo(findUser);
        assertThat(firstUser.getUserSchedules().get("2022-05-03").getSpecialSchedules().size()).isEqualTo(1); //일정 객체추가후 해당 객체에 개인 일정도 추가완료



    }

    /**
     * [특별일정 추가 테스트]
     * 일정객체가 이미 있는경우
     * 1.존재하는 일정 객체에 특별일정 추가
     */
    @Test
    void createSP2() {
        //given


        String date = "2022-05-03";
        SpecialSchedule specialSchedule = new SpecialSchedule("Opic", CertificationScheduleType.APPLY);
        SpecialSchedule specialSchedule2 = new SpecialSchedule("Toeic", CertificationScheduleType.RESULT);


        //when
        User findUser = userRepository.findById(0l);

        UserSchedule createUserSchedule = userScheduleRepository.save(new UserSchedule(date));
        createUserSchedule.getSpecialSchedules().add(specialSchedule);
        findUser.getUserSchedules().put(date,createUserSchedule);

        if(findUser.getUserSchedules().get(date)!=null){
            UserSchedule findUserSchedule = findUser.getUserSchedules().get(date);
            findUserSchedule.getSpecialSchedules().add(specialSchedule2);
        }

        //then
        assertThat(firstUser).isEqualTo(findUser);
        assertThat(firstUser.getUserSchedules().get("2022-05-03").getSpecialSchedules().size()).isEqualTo(2); //일정 객체추가후 해당 객체에 개인 일정도 추가완료

    }


    /**
     * [조회]
     * 특정 일자로 조회
     */
    @Test
    void search1() {
        //given


        String date = "2022-05-03";

        createPS1();
        createAN2();
        createSP2();

        User findUser = userRepository.findById(0l);

        assertThat(firstUser.getUserSchedules().get(date)).isEqualTo(findUser.getUserSchedules().get(date));
    }



}