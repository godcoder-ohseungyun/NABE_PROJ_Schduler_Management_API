package navi.server.domain.schedule.userScheduleSubclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnnouncementSchedule {

    private String anno_id; //사람인 공고번호
    private String Announcement_url;
    private String Announcement_name;
    private String detail;
    private String s_date;
    private String e_date;

    public AnnouncementSchedule(String anno_id,
                                String announcement_url,
                                String announcement_name,
                                String detail,
                                String s_date,
                                String e_date) {
        this.anno_id = anno_id;
        this.Announcement_url = announcement_url;
        this.Announcement_name = announcement_name;
        this.detail = detail;
        this.s_date = s_date;
        this.e_date = e_date;
    }
}
