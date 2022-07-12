package nabe.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nabe.server.dto.announcementApiDTO.ResponseDTO;
import nabe.server.hateoas.HateoasCreator;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class AnnouncementApiController {

    // 사람인 채용공고 RestTemplate
    private final RestTemplate restTemplate;
    private final HateoasCreator hateoasCreator;

    private final String APP_KEY = "jSzmOzrqgCNGWwUGJbBVBxqIPLxwXRz2ZfUGUtAlQOyUmmr5NTka";

    // 채용공고 목록
    @GetMapping("/announcements")
    public ResponseEntity<ResponseDTO> restTemplateTestMethod(HttpServletRequest request) throws UnsupportedEncodingException {

        HttpEntity<String> entity = makeEntity();
        String queryString = "";

        // request에 queryString이 있는지 확인
        try {
            queryString = URLDecoder.decode(request.getQueryString(), "UTF-8");
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        String url = makeUri(queryString);
        ResponseEntity<ResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, ResponseDTO.class);

        // hateoas 적용 위한 임시 코드(스프링 hateoas 적용 후 수정)
        HttpHeaders headers = hateoasCreator.createHeaders("POST","/announcements/user-targets");

        return new ResponseEntity<ResponseDTO>(response.getBody(),headers,HttpStatus.valueOf(200));
    }

    // 매개변수로 받은 param을 사용해 uri 생성 후 반환
    private String makeUri(String param) {
        // param : localhost:8080?keywords=개발자&job_type=1&edu_lv=0&loc_cd=101010&job_mid_cd=2
        // -> keywords=개발자&job_type=1&edu_lv=0&loc_cd=101010&job_mid_cd=2 이부분만 추출

        String defaultUrl = "https://oapi.saramin.co.kr/job-search?access-key=";

        if(param.length() == 0) {
            log.info(String.format("%s%s", defaultUrl, APP_KEY));

            return String.format("%s%s", defaultUrl, APP_KEY);
        }

        else {
            log.info(String.format("%s%s&%s", defaultUrl, APP_KEY, param));

            return String.format("%s%s&%s", defaultUrl, APP_KEY, param);
        }

    }


    // HttpEntity 생성
    private HttpEntity<String> makeEntity() {
        HttpHeaders headers = new HttpHeaders();

        Charset utf8 = Charset.forName("UTF-8");

        MediaType mediaType = new MediaType("application", "json", utf8);

        headers.setContentType(mediaType);

        HttpEntity<String> entity = new HttpEntity<String>("hello api server", headers);

        return entity;
    }

    @PostConstruct
    private void RestTemplateConverterSetting() {

        System.out.println("RestTemplate setting..");
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

    }

}
