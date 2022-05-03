package navi.server.service.schedule;

import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;
import navi.server.domain.user.User;

import java.util.Map;

public interface ScheduleService {

   void createPS(User findUser, String date , PersonalSchedule personalSchedule);
   void createAN(User findUser, String date , AnnouncementSchedule announcementSchedule);
   void createSP(User findUser, String date , SpecialSchedule specialSchedule);


   UserSchedule search(User findUser, String date); // ByOne
   Map<String, UserSchedule> searchAll(User findUser); //all

   void updateUserPS(User findUser, String date, Long psId , PersonalSchedule personalSchedule);

   void deletePS(User findUser,  String date  ,Long psId);
   void deleteAN(User findUser, String date , Long anId);
   void deleteSP(User findUser, String date , Long spId);

}
