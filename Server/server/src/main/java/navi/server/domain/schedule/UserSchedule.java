package navi.server.domain.schedule;

import navi.server.domain.schedule.user_sch.AnnouncementSchedule;
import navi.server.domain.schedule.user_sch.specialSchedule;
import navi.server.domain.schedule.user_sch.PersonalSchedule;

import java.util.HashSet;

public class UserSchedule {
    private String date = "2022-04-29";
    //private String owner = "userId";

    private HashSet<PersonalSchedule> user_sc_list = null; //개인 일정 데이터
    private HashSet<AnnouncementSchedule> user_anno_sc_list = null; //취업 일정 데이터
    private HashSet<specialSchedule> user_lc_sc_list = null; //공식 일정 데이터
}
