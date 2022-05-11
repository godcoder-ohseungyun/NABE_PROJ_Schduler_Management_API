package navi.server.controller.userSchedule.userPersonal;


import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.user.User;
import navi.server.dto.personalScheduleDTO.CreatingPsDTO;
import navi.server.dto.personalScheduleDTO.DeletingPsDTO;
import navi.server.dto.personalScheduleDTO.UpdatingPsDTO;
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
public class PersonalScheduleController {


    private final SchedulerService schedulerService;
    private final UserService userService;
    private final HateosCreator hateosCreator;

    /**
     * 인증 유저의 스캐쥴 전체 조회
     */
    @GetMapping("/user-schedules")
    public ResponseEntity<Map<String, UserSchedule>> readUserSchedule() {
        User master = userService.findUserByUniqueId(0l);

        HttpHeaders headers = hateosCreator.createHeaders("POST","/user-schedules");

        return new ResponseEntity<Map<String, UserSchedule>>(master.getUserSchedules(),headers, HttpStatus.valueOf(200));
    }


    /**
     * 인증 유저의 스캐쥴에 개인 스캐쥴 추가
     */
    @PostMapping("/user-schedules")
    public ResponseEntity<String> addPersonalSchedule(@RequestBody CreatingPsDTO dto) {
        User master = userService.findUserByUniqueId(0l);

        schedulerService.createPersonalSchedule(master, dto);

        HttpHeaders headers = hateosCreator.createHeaders("GET","/user-schedules");

        return new ResponseEntity<String>("ok",headers,HttpStatus.valueOf(200));
    }

    @PutMapping("/user-schedules")
    public ResponseEntity<String> updatePersonalScheduleContents(@RequestBody UpdatingPsDTO dto) {
        User master = userService.findUserByUniqueId(0l);

        //+인증절차 추가해야함
        
        schedulerService.updatePersonalScheduleContents(dto);

        HttpHeaders headers = hateosCreator.createHeaders("GET","/user-schedules");

        return new ResponseEntity<String>("ok",headers,HttpStatus.valueOf(200));
    }

    //이 메서드는 필요한게 맞나 의구심이 든다. 그냥 삭제요청이후 생성요청 보내면 되는건데..없앨까?
//    @PatchMapping("/user-schedules/ps/new")
//    public User updatePersonalSchedule(@RequestBody DeletingPsDTO oldOne ,@RequestBody CreatingPsDTO newOne) {
//        User master = userService.findUserByUniqueId(0l);
//
//        schedulerService.updatePersonalSchedule(master,oldOne,newOne);
//
//        return master;
//    }


    /**
     * 인증 유저의 스캐쥴에 개인 스캐쥴 삭제
     */
    @DeleteMapping("/user-schedules")
    public ResponseEntity<String> deletePersonalSchedule(@RequestBody DeletingPsDTO dto) {
        User master = userService.findUserByUniqueId(0l);

        schedulerService.deletePersonalSchedule(master, dto);

        HttpHeaders headers = hateosCreator.createHeaders("GET","/user-schedules");

        return new ResponseEntity<String>("ok",headers,HttpStatus.valueOf(200));
    }


}
