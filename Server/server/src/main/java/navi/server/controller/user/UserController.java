package navi.server.controller.user;

import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.user.User;
import navi.server.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public User readUser(){

        User master = userService.findUserByUniqueId(0l);

        return master;
    }

    @GetMapping("/user/schedules")
    public Map<String,UserSchedule> readUserSchedules(){

        User master = userService.findUserByUniqueId(0l);

        return master.getUserSchedules();
    }
}
