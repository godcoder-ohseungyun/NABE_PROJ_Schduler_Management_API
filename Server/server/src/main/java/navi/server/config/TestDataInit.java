package navi.server.config;


import lombok.RequiredArgsConstructor;
import navi.server.domain.user.User;
import navi.server.dto.announcementScheduleDTO.AddingAnDTO;
import navi.server.dto.personalScheduleDTO.CreatingPsDTO;
import navi.server.dto.specialScheduleDTO.AddingSpDTO;
import navi.server.service.scheduler.SchedulerService;
import navi.server.service.user.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor //final이 붙은 필드 맴버의 생성자를 자동으로 생성해주는 lombok
public class TestDataInit {

    private final UserService userService;
    private final SchedulerService schedulerService;

    @PostConstruct
    public void init(){
        userService.join(new User("비밀번호","tv0602@naver.com","오승윤")); //최초 가입자
        User find = userService.findUserByUniqueId(0l);

        schedulerService.createAnnouncementSchedule(find,new AddingAnDTO("23213993","test.com","테스트","테스트내용","2020-03-01","2020-04-01"));
        schedulerService.createAnnouncementSchedule(find,new AddingAnDTO("55433321","tes2t.com","테스트2","테스트내용2","2020-03-01","2020-04-01"));

        schedulerService.createPersonalSchedule(find,new CreatingPsDTO("2020-03-01","09:30","10:00",true,"테스트내용1"));
        schedulerService.createPersonalSchedule(find,new CreatingPsDTO("2020-03-01","12:30","14:00",true,"테스트내용2"));

        schedulerService.createSpecialSchedule(find,new AddingSpDTO("Opic","2020-03-01","2020-03-01","2020-03-01","2020-03-01"));
        schedulerService.createSpecialSchedule(find,new AddingSpDTO("Toeic","2020-03-01","2020-03-01","2020-03-01","2020-03-01"));

    }


}
