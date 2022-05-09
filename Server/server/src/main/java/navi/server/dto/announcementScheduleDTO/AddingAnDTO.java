package navi.server.dto.announcementScheduleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddingAnDTO {
    private String date;
    private String anno_id;
    private String announcement_url;
    private String announcement_name;
    private String detail;
}
