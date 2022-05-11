package navi.server.controller.userSchedule.userAnnouncement;

import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.domain.user.User;
import navi.server.dto.announcementScheduleDTO.AddingAnDTO;
import navi.server.hateos.HateosCreator;
import navi.server.service.scheduler.SchedulerService;
import navi.server.service.user.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnnouncementSchduleController {

    private final SchedulerService schedulerService;
    private final UserService userService;
    private final HateosCreator hateosCreator;

    /**
     * 인증된 유저의 타겟 공고 추가
     */
    @PostMapping("/announcements/user-targets")
    public ResponseEntity<AnnouncementSchedule> addTargetAnnouncement(@RequestBody AddingAnDTO dto) {
        User master = userService.findUserByUniqueId(0l);

        AnnouncementSchedule announcementSchedule = schedulerService.createAnnouncementSchedule(master, dto);

        HttpHeaders headers = hateosCreator.createHeaders("GET","/announcements/user-targets");

        return new ResponseEntity<AnnouncementSchedule>(announcementSchedule,headers,HttpStatus.valueOf(200));

    }

    @GetMapping("/announcements/user-targets")
    public ResponseEntity<Map<String, AnnouncementSchedule>> readTargetAnnouncement() {
        User master = userService.findUserByUniqueId(0l);

        HttpHeaders headers = hateosCreator.createHeaders("POST","/announcements/user-targets");

        return new ResponseEntity<Map<String, AnnouncementSchedule>>(master.getAnnouncementSchedules(),headers,HttpStatus.valueOf(200));
    }


    /**
     * 인증된 유저의 타겟 공고 삭제
     */
    @DeleteMapping("/announcements/user-targets/{targetId}")
    public ResponseEntity<String> deleteTargetAnnouncement(@PathVariable  String targetId) {
        User master = userService.findUserByUniqueId(0l);

        schedulerService.deleteAnnouncementSchedule(master, targetId);

        HttpHeaders headers = hateosCreator.createHeaders("GET","/announcements/user-targets");

        return new ResponseEntity<String>("ok",headers,HttpStatus.valueOf(200));

    }
}
