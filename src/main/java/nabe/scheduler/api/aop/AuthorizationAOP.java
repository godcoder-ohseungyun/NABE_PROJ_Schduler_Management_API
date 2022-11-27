package nabe.scheduler.api.aop;

import lombok.RequiredArgsConstructor;
import nabe.scheduler.api.dto.AccessTokenValidationDto;
import nabe.scheduler.api.exception.definedException.AuthenticationImpossibleException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationAOP {

    private final RestTemplate restTemplate ;
    private final String account_management_server_end_point_for_accreditation_check = "15.164.210.47:8000/accounts/kakao/userinfo";

    @Pointcut("execution(* nabe.scheduler.api.controller..*.*(..))")
    private void pointCut() {
    }


    @Before("pointCut()")
    public void isValidAccessToken(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        try {
            RequestEntity<AccessTokenValidationDto> requestForAccountServer = RequestEntity
                    .post(account_management_server_end_point_for_accreditation_check)
                    .body(new AccessTokenValidationDto(request.getHeader("access-token")));

            ResponseEntity<String> response = restTemplate.exchange(requestForAccountServer, String.class); //질의 응답 상태 코드가 4xx , 5xx 일때 예외 발생

        }catch(Exception e){
            throw new AuthenticationImpossibleException("유효하지 않는 사용자",HttpStatus.UNAUTHORIZED);
        }

    }
}


