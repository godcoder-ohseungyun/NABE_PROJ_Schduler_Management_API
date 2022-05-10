package navi.server.controller.announcement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import navi.server.dto.announcementDTO.ResponseDTO;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;


@RestController
@RequiredArgsConstructor
@Slf4j
public class AnnouncementAPIController {

    /**
     * 사람인 채용공고 RestTemplate
     */
    private final RestTemplate restTemplate;

    private final String APP_KEY = "jSzmOzrqgCNGWwUGJbBVBxqIPLxwXRz2ZfUGUtAlQOyUmmr5NTka";


    @GetMapping("/api/ans")
    public ResponseDTO restTempateTestMethod(HttpServletRequest request){

        HttpEntity<String> entity = makeEntity();

        String url = makeUri(request.getQueryString());

        ResponseEntity<ResponseDTO> response = restTemplate.exchange(url,HttpMethod.GET,entity ,ResponseDTO.class);

        ResponseDTO r = response.getBody();

        return r;
    }

    private String makeUri(String param){
        //param : localhost:8080?keywords=개발자&job_type=1&edu_lv=0&loc_cd=101010&job_mid_cd=2
        // -> keywords=개발자&job_type=1&edu_lv=0&loc_cd=101010&job_mid_cd=2 이부분만 추출

        String defaultUrl = "https://oapi.saramin.co.kr/job-search?access-key="+APP_KEY;

        return String.format("%s&",defaultUrl,param);
    }


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
        restTemplate.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("UTF-8")));

    }

}
