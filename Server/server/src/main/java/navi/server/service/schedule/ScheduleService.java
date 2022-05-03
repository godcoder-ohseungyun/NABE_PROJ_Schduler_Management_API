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

   UserSchedule updateUser();

   void deletePS(User findUser,  String date  ,Long psId);

}
