package navi.server.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String password;
    private String email;
    private String name;

    private Map<String, AnnouncementSchedule> announcementSchedules; //취업 일정 데이터
    private Map<Long, SpecialSchedule> specialSchedules; //특별 일정 데이터

    //사용자 스캐쥴 객체
    private Map<String,UserSchedule> userSchedules;

    public User(String password, String email, String name) {
        this.id = id = 0l;
        this.password = password;
        this.email = email;
        this.name = name;
        this.userSchedules = new HashMap<>();
        this.announcementSchedules = new HashMap<>();
        this.specialSchedules = new HashMap<>();
    }
}
