package nabe.server.dto.announcementApiDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    private String id;
    @JsonProperty(value = "url")
    private String announcement_url; //공고 url
    private Position position;
    @JsonProperty(value = "opening-timestamp")
    private String opening_timestamp; //시작일
    @JsonProperty(value = "expiration-timestamp")
    private String expiration_timestamp; //마감일
    private nabe.server.dto.announcementApiDTO.Company company; //정보
}
