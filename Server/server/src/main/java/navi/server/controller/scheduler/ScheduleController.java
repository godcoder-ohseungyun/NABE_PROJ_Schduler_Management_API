package navi.server.controller.scheduler;


import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.user.User;
import navi.server.dto.SpecialScheduleDTO.AddingSpDTO;
import navi.server.dto.announcementScheduleDTO.AddingAnDTO;
import navi.server.dto.announcementScheduleDTO.DeletingAnDTO;
import navi.server.dto.personalScheduleDTO.CreatingPsDTO;
import navi.server.dto.personalScheduleDTO.DeletingDTO;
import navi.server.service.scheduler.SchedulerService;
import navi.server.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {


    private final SchedulerService schedulerService;
    private final UserService userService;

    /**
     * 인증 유저의 스캐쥴 전체 조회
     */
    @GetMapping("/user-schedules")
    public Map<String,UserSchedule> getUserSchedules(){
        User master = userService.findUserByUniqueId(0l);

        return master.getUserSchedules();
    }


    /**
     * 인증 유저의 스캐쥴에 개인 스캐쥴 추가
     */
    @PostMapping("/user-schedules/ps")
    public User addPersonalSchedule(@RequestBody CreatingPsDTO dto){
        User master = userService.findUserByUniqueId(0l);

        schedulerService.createPersonalSchedule(master,dto);

        return master;
    }


    /**
     * 인증 유저의 스캐쥴에 개인 스캐쥴 삭제
     */
    @DeleteMapping("/user-schedules/ps")
    public User deletePersonalSchedule(@RequestBody DeletingDTO dto){
        User master = userService.findUserByUniqueId(0l);

        schedulerService.deletePersonalSchedule(master,dto);

        return master;
    }

    /**
     * 인증된 유저의 타겟 공고 추가
     */
    @PostMapping("/user-target")
    public User addTargetAnnouncement(@RequestBody AddingAnDTO dto){
        User master = userService.findUserByUniqueId(0l);

        schedulerService.createAnnouncementSchedule(master,dto);

        return master;

    }


    /**
     * 인증된 유저의 타겟 공고 삭제
     */
    @DeleteMapping("/user-target")
    public User deleteTargetAnnouncement(@RequestBody DeletingAnDTO dto){
        User master = userService.findUserByUniqueId(0l);

        schedulerService.deleteAnnouncementSchedule(master,dto);

        return master;

    }


    /**
     * 인증된 유저의 특별 일정 추가
     */
    @PostMapping("/sp")
    public User addSpecialSchedule(@RequestBody AddingSpDTO dto){
        User master = userService.findUserByUniqueId(0l);

        schedulerService.createSpecialSchedule(master,dto);

        return master;
    }


    /**
     * 인증된 유저의 특별 일정 삭제
     */
    @DeleteMapping("/sp")
    public User deleteSpecialSchedule(@RequestBody DeletingDTO dto){
        User master = userService.findUserByUniqueId(0l);

        schedulerService.deleteSpecialSchedule(master,dto);

        return master;
    }






}
