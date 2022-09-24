package scheduler.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @detail
 * - 웹 관련 필요한 설정
 * - CORS
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;

    //TODO: FE 웹 서버 배포 완료시, 해당 주소로 origins 제한
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081") //8081로 부터 오는 모든 요청에 CORS 적용
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS); //preflight 결과를 3600초 동안 캐시에 저장
    }
}
