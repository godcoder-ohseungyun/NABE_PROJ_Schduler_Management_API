package navi.server.controller.announcement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import navi.server.dto.announcementDTO.AnnouncementDTO;
import navi.server.dto.announcementDTO.RequestDTO;
import navi.server.dto.announcementDTO.ResponseDTO;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
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
    public ResponseDTO restTempateTestMethod(@RequestBody RequestDTO dto){

        HttpEntity<String> entity = makeEntity();

        String url = makeUri(dto.getKeywords());

        ResponseEntity<ResponseDTO> response = restTemplate.exchange(url,HttpMethod.GET,entity ,ResponseDTO.class);

        ResponseDTO r = response.getBody();

        return r;
    }

    private String makeUri(String keywords){
        String url = "https://oapi.saramin.co.kr/job-search?access-key="+APP_KEY+"&keywords="+keywords;
        return url;
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
