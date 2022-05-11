package navi.server.controller.userSchedule.userSpecial;


import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;
import navi.server.domain.user.User;
import navi.server.dto.personalScheduleDTO.DeletingPsDTO;
import navi.server.dto.specialScheduleDTO.AddingSpDTO;
import navi.server.service.scheduler.SchedulerService;
import navi.server.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SpecialScheduleController {

    private final SchedulerService schedulerService;
    private final UserService userService;

    /**
     * 인증된 유저의 특별 일정 추가
     */
    @PostMapping("/special-schedules")
    public User addSpecialSchedule(@RequestBody AddingSpDTO dto) {
        User master = userService.findUserByUniqueId(0l);

        schedulerService.createSpecialSchedule(master, dto);

        return master;
    }

    @GetMapping("/special-schedules")
    public Map<Long, SpecialSchedule> readSpecialSchedule() {
        User master = userService.findUserByUniqueId(0l);

        return master.getSpecialSchedules();
    }


    /**
     * 인증된 유저의 특별 일정 삭제
     */
    @DeleteMapping("/special-schedules/{targetId}")
    public User deleteSpecialSchedule(@PathVariable Long targetId) {
        User master = userService.findUserByUniqueId(0l);

        schedulerService.deleteSpecialSchedule(master, targetId);

        return master;
    }
}
