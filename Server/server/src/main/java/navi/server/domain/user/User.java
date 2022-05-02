package navi.server.domain.user;

import navi.server.domain.schedule.User_sch;

import java.util.ArrayList;
import java.util.HashSet;

public class User {
    private String loginId;
    private String password;
    private String email;
    private String name;

    //특별일정
    private ArrayList<User_goal> my_goal_list;
    //스캐쥴일정
    private HashSet<User_sch> user_sc_list;

}
