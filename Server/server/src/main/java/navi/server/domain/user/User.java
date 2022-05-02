package navi.server.domain.user;

import navi.server.domain.schedule.UserSchedule;

import java.util.ArrayList;
import java.util.HashSet;

public class User {
    private String loginId;
    private String password;
    private String email;
    private String name;

    //특별일정
    private ArrayList<UserGoal> my_goal_list;
    //스캐쥴일정
    private HashSet<UserSchedule> user_sc_list;

}
