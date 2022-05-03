package navi.server.config;


import lombok.RequiredArgsConstructor;
import navi.server.domain.user.User;
import navi.server.service.user.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor //final이 붙은 필드 맴버의 생성자를 자동으로 생성해주는 lombok
public class TestDataInit {

    private final UserService userService;

    @PostConstruct
    public void init(){
        userService.join(new User("비밀번호","tv0602@naver.com","오승윤")); //최초 가입자

    }
}
