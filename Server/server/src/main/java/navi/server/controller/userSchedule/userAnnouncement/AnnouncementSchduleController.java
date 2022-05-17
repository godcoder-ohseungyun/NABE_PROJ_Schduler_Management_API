package navi.server.controller.userSchedule.userAnnouncement;

import lombok.RequiredArgsConstructor;
import navi.server.common.ResponseDtoMaker;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnnouncementSchduleController {

    private final SchedulerService schedulerService;
    private final UserService userService;
    private final HateosCreator hateosCreator;
    private final ResponseDtoMaker responseDtoMaker;

    /**
     * 인증된 유저의 타겟 공고 추가
     */
    @PostMapping("/{userId}/user-targets")
    public ResponseEntity<AnnouncementSchedule> addTargetAnnouncement(@PathVariable Long userId ,@RequestBody AddingAnDTO dto) {
        User master = userService.findUserByUniqueId(userId);

        AnnouncementSchedule announcementSchedule = schedulerService.createAnnouncementSchedule(master, dto);

        HttpHeaders headers = hateosCreator.createHeaders("GET","/announcements/user-targets");

        return new ResponseEntity<AnnouncementSchedule>(announcementSchedule,headers,HttpStatus.valueOf(200));

    }


    /**
     * 인증된 유저의 타겟 공고 조회
     */
    @GetMapping("/{userId}/user-targets")
    public ResponseEntity<List<AnnouncementSchedule>> readTargetAnnouncement(@PathVariable Long userId) {
        User master = userService.findUserByUniqueId(userId);


        HttpHeaders headers = hateosCreator.createHeaders("POST","/announcements/user-targets");

        return new ResponseEntity<>(responseDtoMaker.convertToAnnouncementResponseDTO(master), headers, HttpStatus.valueOf(200));
    }


    /**
     * 인증된 유저의 타겟 공고 삭제
     */
    @DeleteMapping("/{userId}/user-targets/{targetId}")
    public ResponseEntity<String> deleteTargetAnnouncement(@PathVariable Long userId,@PathVariable  String targetId) {
        User master = userService.findUserByUniqueId(userId);

        schedulerService.deleteAnnouncementSchedule(master, targetId);

        HttpHeaders headers = hateosCreator.createHeaders("GET","/announcements/user-targets");

        return new ResponseEntity<String>("ok",headers,HttpStatus.valueOf(200));

    }
}
