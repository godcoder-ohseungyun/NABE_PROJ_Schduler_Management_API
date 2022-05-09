package navi.server.dto.announcementDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private String keywords;
    private String loc_cd;
    private String ind_cd;
}
