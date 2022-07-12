package nabe.server.dto.announcementApiDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detail {
    @JsonProperty(value = "href")
    private String company_href;
    @JsonProperty(value = "name")
    private String company_name;
}
