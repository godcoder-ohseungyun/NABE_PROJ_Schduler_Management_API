package nabe.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nabe.server.domain.SearchHistory;
import nabe.server.dto.announcementApiDTO.ResponseDTO;
import nabe.server.hateoas.HateoasCreator;
import nabe.server.repository.SearchHistoryRepository;
import nabe.server.service.SearchHistoryService;
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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class AnnouncementApiController {

    // 사람인 채용공고 RestTemplate
    private final RestTemplate restTemplate;
    private final HateoasCreator hateoasCreator;

    private final SearchHistoryRepository searchHistoryRepository;
    private final SearchHistoryService searchHistoryService;

    // 사람인 API에서 사용할 키값
    private final String APP_KEY = "jSzmOzrqgCNGWwUGJbBVBxqIPLxwXRz2ZfUGUtAlQOyUmmr5NTka";

    // Member 서버에서 받을 memberId(차후 수정);
    private Long memberId = 1L;

    // 채용공고 목록 조회
    @GetMapping("/announcements")
    public ResponseEntity<ResponseDTO> getAnnouncements(HttpServletRequest request) throws UnsupportedEncodingException {

        HttpEntity<String> entity = makeEntity();
        String queryString = "";

        // request에 queryString이 있는지 확인
        try {
            queryString = URLDecoder.decode(request.getQueryString(), "UTF-8");
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        // queryString 파싱해서 DB에 저장
        saveQueryStringToDB(request);

        String url = makeUri(queryString);
        ResponseEntity<ResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, ResponseDTO.class);

        // hateoas 적용 위한 임시 코드(스프링 hateoas 적용 후 수정)
        HttpHeaders headers = hateoasCreator.createHeaders("POST","/announcements/user-targets");

        return new ResponseEntity<ResponseDTO>(response.getBody(),headers,HttpStatus.valueOf(200));
    }

    @GetMapping("/recommendations")
    public ResponseEntity<ResponseDTO> recommendAnnouncements() {
        Map<String, List> mostUsedSearchConditions = searchHistoryRepository.findMostUsedSearchConditions();

        log.info("mostUsedSearchConditions = " + mostUsedSearchConditions);

        HttpEntity<String> entity = makeEntity();

        // 검색 조건 조합해서 queryString 만들기
        String queryString = "?";
        queryString = createQueryStringForRecommendation(mostUsedSearchConditions, queryString);
        log.info("queryString = " + queryString);

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

    private void saveQueryStringToDB(HttpServletRequest request) {
        String keywords = request.getParameter("keywords");
        String loc_cd = request.getParameter("loc_cd");
        String job_cd = request.getParameter("job_cd");
        String job_type = request.getParameter("job_type");
        String edu_lv = request.getParameter("edu_lv");
        String published = request.getParameter("published");
        String deadline = request.getParameter("deadline");

//        String[] job_cd_ls = request.getParameterValues("job_cd");

        SearchHistory history = SearchHistory.createSearchHistory(memberId, keywords, loc_cd, job_cd, job_type, edu_lv, published, deadline);
        searchHistoryService.saveSearchHistory(history);
    }

    private String createQueryStringForRecommendation(Map<String, List> mostUsedSearchConditions, String queryString) {
        // keywords 속성
        List<String> keywords = mostUsedSearchConditions.get("keywords");

        // 리스트에서 null값 제거
        keywords.removeAll(Collections.singletonList(null));

        // null값 제거 이후 검색 조건 리스트의 iterator 생성
        Iterator<String> keywords_iterator = keywords.iterator();

        if(keywords_iterator.hasNext()) {
            // 리스트에 항목있으면 쿼리 쿼리파라미터의 키값 추가
            queryString += "keywords=";

            while (true) {
                // 쿼리 파라미터의 value값 추가
                queryString += keywords_iterator.next();

                // 다음 항목 있으면 쉼표, 없으면 다음 항목으로 넘어가기 위해 & 추가
                if (keywords_iterator.hasNext()) {
                    queryString += ", ";
                } else {
                    queryString += "&";
                    break;
                }
            }
        }

        // loc_cd 속성
        List<String> loc_cd = mostUsedSearchConditions.get("loc_cd");
        loc_cd.removeAll(Collections.singletonList(null));

        Iterator<String> loc_cd_iterator = loc_cd.iterator();

        if(loc_cd_iterator.hasNext()) {
            queryString += "loc_cd=";

            while (true) {
                queryString += loc_cd_iterator.next();

                if(loc_cd_iterator.hasNext()) {
                    queryString += ", ";
                } else {
                    queryString += "&";
                    break;
                }
            }
        }

        // job_cd 속성
        List<String> job_cd = mostUsedSearchConditions.get("job_cd");
        job_cd.removeAll(Collections.singletonList(null));

        Iterator<String> job_cd_iterator = job_cd.iterator();

        if(job_cd_iterator.hasNext()) {
            queryString += "job_cd=";

            while (true) {
                queryString += job_cd_iterator.next();

                if(job_cd_iterator.hasNext()) {
                    queryString += ", ";
                } else {
                    queryString += "&";
                    break;
                }
            }
        }

        // job_type 속성
        List<String> job_type = mostUsedSearchConditions.get("job_type");
        job_type.removeAll(Collections.singletonList(null));

        Iterator<String> job_type_iterator = job_type.iterator();

        if(job_type_iterator.hasNext()) {
            queryString += "job_type=";

            while (true) {
                queryString += job_type_iterator.next();

                if(job_type_iterator.hasNext()) {
                    queryString += ", ";
                } else {
                    queryString += "&";
                    break;
                }
            }
        }

        // edu_lv 속성
        List<String> edu_lv = mostUsedSearchConditions.get("edu_lv");
        edu_lv.removeAll(Collections.singletonList(null));

        Iterator<String> edu_lv_iterator = edu_lv.iterator();

        if(edu_lv_iterator.hasNext()) {
            queryString += "edu_lv=";

            while (true) {
                queryString += edu_lv_iterator.next();

                if(edu_lv_iterator.hasNext()) {
                    queryString += ", ";
                } else {
                    break;
                }
            }
        }


        return queryString;
    }

}
