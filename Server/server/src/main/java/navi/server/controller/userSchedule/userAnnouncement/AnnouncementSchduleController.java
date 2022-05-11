package navi.server.controller.userSchedule.userAnnouncement;

import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.domain.user.User;
import navi.server.dto.announcementScheduleDTO.AddingAnDTO;
import navi.server.service.scheduler.SchedulerService;
import navi.server.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnnouncementSchduleController {

    private final SchedulerService schedulerService;
    private final UserService userService;

    /**
     * 인증된 유저의 타겟 공고 추가
     */
    @PostMapping("/announcements/user-targets")
    public User addTargetAnnouncement(@RequestBody AddingAnDTO dto) {
        User master = userService.findUserByUniqueId(0l);

        schedulerService.createAnnouncementSchedule(master, dto);

        return master;

    }

    @GetMapping("/announcements/user-targets")
    public Map<String, AnnouncementSchedule> readTargetAnnouncement() {
        User master = userService.findUserByUniqueId(0l);

        return master.getAnnouncementSchedules();
    }


    /**
     * 인증된 유저의 타겟 공고 삭제
     */
    @DeleteMapping("/announcements/user-targets/{targetId}")
    public User deleteTargetAnnouncement(@PathVariable  String targetId) {
        User master = userService.findUserByUniqueId(0l);

        schedulerService.deleteAnnouncementSchedule(master, targetId);

        return master;

    }
}
