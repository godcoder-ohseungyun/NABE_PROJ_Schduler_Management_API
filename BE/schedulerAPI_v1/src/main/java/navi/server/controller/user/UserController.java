package navi.server.controller.user;

import lombok.RequiredArgsConstructor;
import navi.server.common.ResponseDtoMaker;
import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.user.User;
import navi.server.dto.responseDTO.UserResponseDTO;
import navi.server.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseDtoMaker responseDtoMaker;

    @GetMapping("/{userId}")
    public UserResponseDTO readUser(@PathVariable Long userId){

        User master = userService.findUserByUniqueId(userId);

        return responseDtoMaker.convertToUserResponseDTO(master);
    }

}
