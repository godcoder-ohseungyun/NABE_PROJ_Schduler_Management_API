package scheduler.api.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scheduler.api.aop.aopDto.AccessToken;
import scheduler.api.exception.userDefinedException.ValidatedException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpHeaders;
import java.util.HashMap;

/**
 * @title: 인가 에이오피
 * @contents: 사용자 요청시 동봉한 엑세스 토큰이 유효한지 인증 서버에 질의를 던지는 업무를 수행합니다.
 * 모든 컨트롤러 메서드 실행 시점에 적용됩니다.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationAOP {

    private final RestTemplate restTemplate ;
    private final String url = "http://localhost:8080/fortest/accesstoken";

    //com/example/aop/controller 패키지 하위 클래스들 전부 적용하겠다고 지점 설정
    @Pointcut("execution(* scheduler.api.controller..*.*(..))")
    private void pointCut() {
    }

    /**
     *  **아직 인증서버 배포 전이라 요청부 주석 처리**
     *  전부 통과처리 -> 테스트 수행 지장 없음
     */
    @Before("pointCut()")
    public void isValidAccessToken(JoinPoint joinPoint) throws Exception {
        //요청 http 조회
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();


        try {
            //인증 서버로 보낼 요청 생성
            RequestEntity<AccessToken> requestForAccountServer = RequestEntity
                    .post(url)
                    .body(new AccessToken(request.getHeader("access-token")));

            //응답 상태 코드가 4xx , 5xx 일때 restTemplate에서 예외 발생시킴
            //ResponseEntity<String> response = restTemplate.exchange(requestForAccountServer, String.class);

        }catch(Exception e){
            throw new ValidatedException("유효하지 않는 사용자",HttpStatus.UNAUTHORIZED);
        }


    }
}


