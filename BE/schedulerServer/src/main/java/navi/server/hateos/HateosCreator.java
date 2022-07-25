package navi.server.hateos;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class HateosCreator {
    
    private final String API_DOC_LINK = "https://documenter.getpostman.com/view/17055046/UyxgK8Ty";
    
    public HttpHeaders createHeaders(String method,String nextUrl){
        
        HttpHeaders headers = new HttpHeaders();

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath() // 기본 uri
                .path(nextUrl)
                .build()
                .toUri();

        headers.set("Location",String.format("%s %s",method , location));
        headers.set("Link",API_DOC_LINK);
        
        return headers;
    }
}
