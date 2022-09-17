package scheduler.api.aop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import scheduler.api.aop.aopDto.AccessToken;
import scheduler.api.domain.an.AnnouncementSchedule;
import scheduler.api.dto.an.CreatingAnnouncementDto;
import scheduler.api.exception.userDefinedException.DuplicateDataException;
import scheduler.api.exception.userDefinedException.ValidatedException;

/**
 * Account Server 배포 전까지만 테스트룰 위해 사용될 임시 accesstoken 인증 로직 입니다. -> '무조건 통과' 입니다.
 */
@RestController
@RequestMapping("/fortest")
@RequiredArgsConstructor
public class AccountController {

    @PostMapping("/accesstoken")
    public ResponseEntity<String> get(@RequestBody AccessToken accessToken){

        return new ResponseEntity<String>("ok",null, HttpStatus.valueOf(200));
    }


}
