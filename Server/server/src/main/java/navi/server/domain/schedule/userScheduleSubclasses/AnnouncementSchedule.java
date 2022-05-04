package navi.server.domain.schedule.userScheduleSubclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnnouncementSchedule {

    private String anno_id ="공고번호"; //사람인 공고번호
    private String Announcement_url = "사람인 url";
    private String Announcement_name = "공고명";
    private String detail = "그외 축약정보";

    public AnnouncementSchedule(String anno_id, String announcement_url, String announcement_name, String detail) {
        this.anno_id = anno_id;
        this.Announcement_url = announcement_url;
        this.Announcement_name = announcement_name;
        this.detail = detail;
    }
}
