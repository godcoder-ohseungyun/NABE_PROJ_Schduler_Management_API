package navi.server.service.scheduler;

import navi.server.domain.user.User;
import navi.server.dto.specialScheduleDTO.AddingSpDTO;
import navi.server.dto.announcementScheduleDTO.AddingAnDTO;
import navi.server.dto.personalScheduleDTO.CreatingPsDTO;
import navi.server.dto.personalScheduleDTO.DeletingPsDTO;
import navi.server.dto.personalScheduleDTO.UpdatingPsDTO;

public interface SchedulerService {

    /**
     * 개인일정
     */
    void createPersonalSchedule(User loginUser, CreatingPsDTO dto);
    
    //동일 날짜에서 내용만 변경
    void updatePersonalScheduleContents(UpdatingPsDTO dto);

    //새로운 날짜로 이동
    //이 메서드는 필요한게 맞나 의구심이 든다. 그냥 삭제요청이후 생성요청 보내면 되는건데..없앨까?
    void updatePersonalSchedule(User loginUser, DeletingPsDTO oldOne , CreatingPsDTO newOne);

    void deletePersonalSchedule( User loginUser , DeletingPsDTO dto);

    /**
     * 공고일정
     */
    void createAnnouncementSchedule(User loginUser, AddingAnDTO dto);

    void deleteAnnouncementSchedule(User loginUser, String targetId);


    /**
     * 특별일정
     */
    void createSpecialSchedule(User loginUser, AddingSpDTO dto);
    
    void deleteSpecialSchedule(User loginUser, Long targetId);

    

    

}
