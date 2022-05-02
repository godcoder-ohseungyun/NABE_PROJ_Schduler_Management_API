import java.lang.annotation.Target;

public class User{ 
 
    private String email;
    private String loginId;
    private Stirng password;
    private String name;

    private ArrayList<User_goals> my_goal_list = null; //장기 목표
    //타겟기업 리스트 유지 맴버 필요
    
  }

  //
class User_goals {
   private String name;
   private Stirng deadline = "2023-06-02";
}



/**========================================================================
 * 어떤 유저의 해당 날짜 일정 객체
 */
public class UserSchedule{ 

  private String date = "2022-04-29";
  //private String owner = "userId";

  private HashSet<User_ps_sc> user_sc_list = null; //개인 일정 데이터
  private HashSet<User_Anno_sc> user_anno_sc = null; //취업 일정 데이터
  private HashSet<User_lc_sc> user_lc_sc = null; //공식 일정 데이터

}


//맴버 객체

//사용자가 직접 적용하는 스캐쥴
class PersonalSchedule{
    //private String date = "2022-04-29";
    //private String owner = "userId";
    private String s_time = "08:30";
    private String e_time = "09:30";
    private boolean observation = true; //모니터링 필요시 ui에 d-day line 제공
    private String detail = "내용";
}


//앱에서 자동 적용하는 스캐쥴(취업 일정)
class AnnouncementSchedule{
    //private String date = "2022-04-29";
    //private String owner = "userId";
    private String anno_id ="공고번호"; //사람인 공고번호
    private String Announcement_url = "사람인 url";
    private String Announcement_name = "공고명";
    private String detail = "그외 축약정보";
}

//앱에서 자동 적용하는 스캐쥴(공식자격 일정)
class specialSchedule{
    //private String date = "2022-04-29";
    //private String owner = "userId";
    private String type = "v"; //or "r" 신청일 / 결과 발표일
}

//========================================================================
