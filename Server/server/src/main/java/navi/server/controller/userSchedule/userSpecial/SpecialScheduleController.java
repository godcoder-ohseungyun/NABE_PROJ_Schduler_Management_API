package navi.server.controller.userSchedule.userSpecial;


import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;
import navi.server.domain.user.User;
import navi.server.dto.personalScheduleDTO.DeletingPsDTO;
import navi.server.dto.specialScheduleDTO.AddingSpDTO;
import navi.server.hateos.HateosCreator;
import navi.server.service.scheduler.SchedulerService;
import navi.server.service.user.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SpecialScheduleController {

    private final SchedulerService schedulerService;
    private final UserService userService;
    private final HateosCreator hateosCreator;

    /**
     * 인증된 유저의 특별 일정 추가
     */
    @PostMapping("/special-schedules")
    public ResponseEntity<SpecialSchedule> addSpecialSchedule(@RequestBody AddingSpDTO dto) {
        User master = userService.findUserByUniqueId(0l);

        SpecialSchedule specialSchedule =  schedulerService.createSpecialSchedule(master, dto);

        HttpHeaders headers = hateosCreator.createHeaders("GET","/user-schedules");

        return new ResponseEntity<SpecialSchedule>(specialSchedule,headers, HttpStatus.valueOf(200));
    }

    @GetMapping("/special-schedules")
    public ResponseEntity<Map<Long, SpecialSchedule>> readSpecialSchedule() {
        User master = userService.findUserByUniqueId(0l);

        HttpHeaders headers = hateosCreator.createHeaders("POST","/user-schedules");

        return new ResponseEntity<Map<Long, SpecialSchedule>>(master.getSpecialSchedules(),headers,HttpStatus.valueOf(200));
    }


    /**
     * 인증된 유저의 특별 일정 삭제
     */
    @DeleteMapping("/special-schedules/{targetId}")
    public ResponseEntity<String> deleteSpecialSchedule(@PathVariable Long targetId) {
        User master = userService.findUserByUniqueId(0l);

        schedulerService.deleteSpecialSchedule(master, targetId);

        HttpHeaders headers = hateosCreator.createHeaders("GET","/user-schedules");

        return new ResponseEntity<String>("ok",headers,HttpStatus.valueOf(200));
    }
}
