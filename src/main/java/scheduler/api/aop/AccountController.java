package scheduler.api.aop;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
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
import scheduler.api.rest.RestHeaderCreator;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Account Server 배포 전까지만 테스트룰 위해 사용될 임시 accesstoken 인증 로직 입니다. -> '무조건 통과' 입니다.
 */
@RestController
@RequestMapping("/fortest")
@RequiredArgsConstructor
public class AccountController {

    private final RestHeaderCreator restHeaderCreator;

    @PostMapping("/accesstoken")
    public ResponseEntity<String> get(@RequestBody AccessToken accessToken, HttpServletRequest request){


        ResponseEntity<String> response = new ResponseEntity<String>("ok",
                restHeaderCreator.createRestfulHeader(AccountController.class,
                        "/accesstoken","[POST]")
                , HttpStatus.valueOf(200));

        return response;
    }



}
