package navi.server.service.scheduler;


import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;
import navi.server.domain.user.User;
import navi.server.dto.specialScheduleDTO.AddingSpDTO;
import navi.server.dto.announcementScheduleDTO.AddingAnDTO;
import navi.server.dto.personalScheduleDTO.CreatingPsDTO;
import navi.server.dto.personalScheduleDTO.DeletingPsDTO;
import navi.server.dto.personalScheduleDTO.UpdatingPsDTO;
import navi.server.service.schedule.announcementSchedule.AnnouncementScheduleService;
import navi.server.service.schedule.personalSchedule.PersonalScheduleService;
import navi.server.service.schedule.specialSchedule.SpecialScheduleService;
import navi.server.service.schedule.userSchedule.UserScheduleService;
import org.springframework.stereotype.Service;

/**
 * 인증 사용자의 스캐쥴을 추가 삭제 수정 조회 하는 class
 */
@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final UserScheduleService userScheduleService;
    private final PersonalScheduleService personalScheduleService;
    private final AnnouncementScheduleService announcementScheduleService;
    private final SpecialScheduleService specialScheduleService;

    //개인일정 추가
    @Override
    public PersonalSchedule createPersonalSchedule(User loginUser, CreatingPsDTO dto) {

        String date = dto.getDate();

        PersonalSchedule createdPersonalSchedule = personalScheduleService.createPersonalSchedule(
                new PersonalSchedule(dto.getS_time(),
                        dto.getE_time(),
                        dto.isObservation(),
                        dto.getDetail()));

        //해당 일자객체 없는경우
        if (loginUser.getUserSchedules().get(date) == null) {
            //해당 일자객체 생성
            UserSchedule createdUserSchedule = userScheduleService.createUserSchedule(new UserSchedule(date));
            // 생성된 객체에 개인일정 추가
            createdUserSchedule.getPersonalSchedules().put(createdPersonalSchedule.getId(), createdPersonalSchedule);
            //유저 객체에 스캐쥴 추가
            loginUser.getUserSchedules().put(date, createdUserSchedule);

        }
        //해당 일자객체 있는경우
        else {
            //해당 일자객체 조회
            UserSchedule findUserSchedule = loginUser.getUserSchedules().get(date);
            //개인일정 추가
            findUserSchedule.getPersonalSchedules().put(createdPersonalSchedule.getId(), createdPersonalSchedule);
        }

        return createdPersonalSchedule;
    }

    //공고일정 추가
    @Override
    public AnnouncementSchedule createAnnouncementSchedule(User loginUser, AddingAnDTO dto) {

        if(!announcementScheduleService.isIn(dto.getAnno_id())){
            announcementScheduleService.createAnnouncementSchedule(new AnnouncementSchedule(dto.getAnno_id(),
                    dto.getAnnouncement_url(),
                    dto.getAnnouncement_name(),
                    dto.getDetail(),
                    dto.getS_date(),
                    dto.getE_date()));
        }

        AnnouncementSchedule findAnnouncementSchedule = announcementScheduleService.findAnnouncementSchedule(dto.getAnno_id());

        if(findAnnouncementSchedule!=null){
            loginUser.getAnnouncementSchedules().put(findAnnouncementSchedule.getAnno_id(),findAnnouncementSchedule);
        }

        return announcementScheduleService.findAnnouncementSchedule(dto.getAnno_id());
    }

    //특별일정 추가
    @Override
    public SpecialSchedule createSpecialSchedule(User loginUser, AddingSpDTO dto) {


        if(!specialScheduleService.isIn(dto.getId())){
            specialScheduleService.createSpecialSchedule(new SpecialSchedule(dto.getName(),
                    dto.getCertificationScheduleType(),
                    dto.getS_date(),
                    dto.getE_date()));
        }

        SpecialSchedule findSpecialSchedule = specialScheduleService.findSpecialSchedule(dto.getId());

        if(findSpecialSchedule!=null){
            loginUser.getSpecialSchedules().put(findSpecialSchedule.getId(),findSpecialSchedule);
        }

        return specialScheduleService.findSpecialSchedule(dto.getId());
    }


    //개인일정 삭제
    @Override
    public void deletePersonalSchedule(User loginUser, Long targetId , DeletingPsDTO dto) {
        loginUser.getUserSchedules().get(dto.getTargetDate()).getPersonalSchedules().remove(targetId);
        personalScheduleService.deletePersonalSchedule(targetId);
    }

    //공고일정 삭제
    @Override
    public void deleteAnnouncementSchedule(User loginUser, String targetId) {
        loginUser.getAnnouncementSchedules().remove(targetId);
        //한번저장된 객체는 유지함
    }

    //특별일정 삭제
    @Override
    public void deleteSpecialSchedule(User loginUser, Long targetId) {
        loginUser.getSpecialSchedules().remove(targetId);
    }



    //개인일정 내용만 변경
    @Override
    public PersonalSchedule updatePersonalScheduleContents(UpdatingPsDTO dto) {
        PersonalSchedule newOne = new PersonalSchedule(dto.getS_time(), dto.getE_time(), dto.isObservation(), dto.getDetail());

        personalScheduleService.updatePersonalSchedule(dto.getTargetId(),newOne);

        return newOne;

    }

    //개인일정 날짜변경
    //이 메서드는 필요한게 맞나 의구심이 든다. 그냥 삭제요청이후 생성요청 보내면 되는건데..없앨까?
//    @Override
//    public PersonalSchedule updatePersonalSchedule(User loginUser, DeletingPsDTO oldOne , CreatingPsDTO newOne) {
//        deletePersonalSchedule(loginUser,oldOne);
//        createPersonalSchedule(loginUser,newOne);
//    }
    


}
