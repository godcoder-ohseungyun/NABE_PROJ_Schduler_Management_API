package navi.server.service.scheduler;

import navi.server.domain.user.User;
import navi.server.dto.SpecialScheduleDTO.AddingSpDTO;
import navi.server.dto.announcementScheduleDTO.AddingAnDTO;
import navi.server.dto.delDTO.DeletingDTO;
import navi.server.dto.personalScheduleDTO.CreatingPsDTO;
import navi.server.dto.personalScheduleDTO.DeletingPsDTO;
import navi.server.dto.personalScheduleDTO.UpdatingPsDTO;

public interface SchedulerService {
    void createPersonalSchedule(User loginUser, CreatingPsDTO dto);

    void createAnnouncementSchedule(User loginUser, AddingAnDTO dto);

    //특별일정 추가
    void createSpecialSchedule(User loginUser, AddingSpDTO dto);



    //개인일정 삭제
    void deletePersonalSchedule( User loginUser , DeletingPsDTO dto);

    void deleteAnnouncementSchedule(User loginUser, DeletingDTO dto);

    //특별일정 삭제
    void deleteSpecialSchedule(User loginUser, DeletingPsDTO dto);

    //개인일정 시간 및 내용 변경(날짜유지)
    void updatePersonalScheduleDetail(Long targetId , CreatingPsDTO dto);

    //개인일정 날짜변경
    void updatePersonalScheduleDate( User loginUser , UpdatingPsDTO dto);

}
