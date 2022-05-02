package navi.server.domain.schedule;

import navi.server.domain.schedule.user_sch.User_Anno_sc;
import navi.server.domain.schedule.user_sch.User_lc_sc;
import navi.server.domain.schedule.user_sch.User_ps_sc;

import java.util.HashSet;

public class User_sch {
    private String date = "2022-04-29";
    //private String owner = "userId";

    private HashSet<User_ps_sc> user_sc_list = null; //개인 일정 데이터
    private HashSet<User_Anno_sc> user_anno_sc_list = null; //취업 일정 데이터
    private HashSet<User_lc_sc> user_lc_sc_list = null; //공식 일정 데이터
}
