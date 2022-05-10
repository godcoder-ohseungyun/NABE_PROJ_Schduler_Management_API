package navi.server.dto.announcementApiDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    private String id;
    private Company company; //정보
    @JsonProperty(value = "opening-timestamp")
    private String opening_timestamp; //시작일
    @JsonProperty(value = "expiration-timestamp")
    private String expiration_timestamp; //마감일
}
