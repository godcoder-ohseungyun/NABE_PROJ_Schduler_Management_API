package we.graduationProj.scheduler.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import we.graduationProj.scheduler.dto.KakaoAccessTokenDto;
import we.graduationProj.scheduler.dto.SocialDto;
import we.graduationProj.scheduler.dto.TokenDto;
import we.graduationProj.scheduler.dto.UserDto;
import we.graduationProj.scheduler.jwt.JwtFilter;
import we.graduationProj.scheduler.jwt.TokenProvider;
import we.graduationProj.scheduler.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final RestTemplate restTemplate;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public AuthController(TokenProvider tokenProvider,
                          AuthenticationManagerBuilder authenticationManagerBuilder,
                          RestTemplate restTemplate, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.restTemplate = restTemplate;
        this.userService = userService;
    }


    /**
     * 인증 코드를 가지고 카카오로 부터 토큰을 발급받아 사용자 데이터를 추출해 회원가입 및 토큰발급(로그인) 진행
     */
    @PostMapping("/kakao_authenticate")
    public ResponseEntity<TokenDto> authorize(@RequestParam String code) {


        /**
         * 엑세스토큰 처리부
         */
        String kakaoAccessToken = getAccessToken(code);


        /**
         * 인가 처리부
         */
        String userInfo = getUserInfo(kakaoAccessToken);

        SocialDto socialDto = verificationKakao(kakaoAccessToken,userInfo);

        /**
         * 로그인 및 회원가입 처리
         */
        UserDto userDto = new UserDto();

        userDto.setUsername(socialDto.getEmail());
        userDto.setPassword(socialDto.getEmail());
        userDto.setNickname(socialDto.getUserName());

        userService.signup(userDto); //회원가입 진행 (내부적으로 중복회원 거부)

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
        
    }


    private String getAccessToken(String code){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "271fcfa8f041287ebda663f801b1022b");
        params.add("redirect_uri", "https://dev.example.com:8081/login" ); //+ "/oauth2/code/kakao" <-아마 이건 프론트 url인듯
        params.add("code", code);
        params.add("client_secret", "xESqxvJw7DcsMoHl9yVOo2cWFJbpExvl");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kauth.kakao.com/oauth/token";

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);


        //AccessToken 받음
        try {
            return objectMapper.readValue(response.getBody(), KakaoAccessTokenDto.class).getAccess_token();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }


    private String getUserInfo(String kakaoAccessToken){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + kakaoAccessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v2/user/me";

        return restTemplate.postForObject(url, request, String.class);
    }


    private SocialDto verificationKakao(String  kakaoAccessToken  , String userInfo){
        SocialDto socialDto = new SocialDto();

        try {
            JsonNode jsonNode = objectMapper.readTree(userInfo);
            String email = String.valueOf(jsonNode.get("kakao_account").get("email"));
            socialDto.setEmail("kakao_" + email.substring(1, email.length() - 1));
            String name = String.valueOf(jsonNode.get("kakao_account").get("profile").get("nickname"));
            socialDto.setUserName(name.substring(1, name.length() - 1));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return socialDto;
    }
}
