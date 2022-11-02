package scheduler.api.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scheduler.api.aop.aopDto.AccessTokenValidationDto;
import scheduler.api.exception.userDefinedException.ValidatedException;

import javax.servlet.http.HttpServletRequest;

/**
 * @detail
 * - 사용자 요청시 동봉한 엑세스 토큰이 유효한지 인증 서버에 질의를 던지는 업무를 수행합니다.
 * - 모든 컨트롤러 메서드 실행 시점에 먼저 적용됩니다.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationAOP {

    private final RestTemplate restTemplate ;
    private final String account_management_server_end_point_for_accreditation_check = "15.164.210.47:8000/accounts/kakao/userinfo";

    @Pointcut("execution(* scheduler.api.controller..*.*(..))")
    private void pointCut() {
    }


    @Before("pointCut()")
    public void isValidAccessToken(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        try {
            RequestEntity<AccessTokenValidationDto> requestForAccountServer = RequestEntity
                    .post(account_management_server_end_point_for_accreditation_check)
                    .body(new AccessTokenValidationDto(request.getHeader("access-token")));

            ResponseEntity<String> response = restTemplate.exchange(requestForAccountServer, String.class); //질의 응답 상태 코드가 4xx , 5xx 일때 예외 발생

        }catch(Exception e){
            throw new ValidatedException("유효하지 않는 사용자",HttpStatus.UNAUTHORIZED);
        }

    }
}


