package navi.server.controller.scheduler;


import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;
import navi.server.domain.user.User;
import navi.server.dto.PersonalScheduleDTO;
import navi.server.repository.schedule.UserScheduleRepository;
import navi.server.repository.schedule.UserScheduleSubclasses.PersonalScheduleRepository;
import navi.server.service.schedule.ScheduleService;
import navi.server.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {


    private final ScheduleService scheduleService;
    private final PersonalScheduleRepository personalScheduleRepository;

    @PostMapping("/user-schedule/ps")
    public String addPersonalSchedule(@RequestBody PersonalScheduleDTO psDTO){

        //jwt 유저인증된 객체
        User findUser = new User();

        PersonalSchedule createPersonalSchedule = personalScheduleRepository.save(new PersonalSchedule(psDTO.getS_time(),psDTO.getE_time(),psDTO.isObservation(),psDTO.getDetail()));

        scheduleService.createPS(findUser,psDTO.getDate(),createPersonalSchedule);

        return "ok";
    }
}
