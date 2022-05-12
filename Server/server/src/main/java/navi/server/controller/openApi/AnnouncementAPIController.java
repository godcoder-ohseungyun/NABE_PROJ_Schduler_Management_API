package navi.server.controller.openApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import navi.server.dto.announcementApiDTO.RequestDTO;
import navi.server.dto.announcementApiDTO.ResponseDTO;
import navi.server.hateos.HateosCreator;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
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
public class AnnouncementAPIController {

    /**
     * 사람인 채용공고 RestTemplate
     */
    private final RestTemplate restTemplate;
    private final HateosCreator hateosCreator;

    private final String APP_KEY = "jSzmOzrqgCNGWwUGJbBVBxqIPLxwXRz2ZfUGUtAlQOyUmmr5NTka";


    @GetMapping("/announcements")
    public ResponseEntity<ResponseDTO> restTempateTestMethod(HttpServletRequest request) throws UnsupportedEncodingException {

        HttpEntity<String> entity = makeEntity();

        String queryString = URLDecoder.decode(request.getQueryString(), "UTF-8");

        String url = makeUri(queryString);

        ResponseEntity<ResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, ResponseDTO.class);

        HttpHeaders headers = hateosCreator.createHeaders("POST","/announcements/user-targets");

        return new ResponseEntity<ResponseDTO>(response.getBody(),headers,HttpStatus.valueOf(200));
    }

    private String makeUri(String param) {
        //param : localhost:8080?keywords=개발자&job_type=1&edu_lv=0&loc_cd=101010&job_mid_cd=2
        // -> keywords=개발자&job_type=1&edu_lv=0&loc_cd=101010&job_mid_cd=2 이부분만 추출

        String defaultUrl = "https://oapi.saramin.co.kr/job-search?access-key=";

        log.info(String.format("%s%s&%s", defaultUrl, APP_KEY, param));

        return String.format("%s%s&%s", defaultUrl, APP_KEY, param);
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
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

    }

}
