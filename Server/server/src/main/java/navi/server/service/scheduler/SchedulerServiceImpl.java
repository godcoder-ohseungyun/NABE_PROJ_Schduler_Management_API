package navi.server.service.scheduler;


import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;
import navi.server.domain.user.User;
import navi.server.dto.SpecialScheduleDTO.AddingSpDTO;
import navi.server.dto.announcementScheduleDTO.AddingAnDTO;
import navi.server.dto.delDTO.DeletingDTO;
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
    public void createPersonalSchedule(User loginUser, CreatingPsDTO dto) {

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
    }

    //공고일정 추가
    @Override
    public void createAnnouncementSchedule(User loginUser, AddingAnDTO dto) {

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

    }

    //특별일정 추가
    @Override
    public void createSpecialSchedule(User loginUser, AddingSpDTO dto) {


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
    }


    //개인일정 삭제
    @Override
    public void deletePersonalSchedule(User loginUser, DeletingPsDTO dto) {
        loginUser.getUserSchedules().get(dto.getTargetDate()).getPersonalSchedules().remove(dto.getTargetId());
        personalScheduleService.deletePersonalSchedule(dto.getTargetId());
    }

    //공고일정 삭제
    @Override
    public void deleteAnnouncementSchedule(User loginUser, DeletingDTO dto) {
        loginUser.getAnnouncementSchedules().remove(dto.getTargetAnId());
        //한번저장된 객체는 유지함
    }

    //특별일정 삭제
    @Override
    public void deleteSpecialSchedule(User loginUser, DeletingPsDTO dto) {
        loginUser.getSpecialSchedules().remove(dto.getTargetId());
    }




    //개인일정 시간 및 내용 변경(날짜유지)
    @Override
    public void updatePersonalScheduleDetail(Long targetId, CreatingPsDTO dto) {

        personalScheduleService.updatePersonalSchedule(targetId, new PersonalSchedule(dto.getS_time(), dto.getE_time(), dto.isObservation(), dto.getDetail()));

    }

    //개인일정 날짜변경
    @Override
    public void updatePersonalScheduleDate(User loginUser, UpdatingPsDTO dto) {
        //해당 개인일정을 원래 객체에서 제거
        PersonalSchedule ps = loginUser.getUserSchedules().get(dto.getOldDate()).getPersonalSchedules().remove(dto.getTargetId());
        //해당 개인일정을 알맞은 날짜에 추가
        loginUser.getUserSchedules().get(dto.getNewDate()).getPersonalSchedules().put(ps.getId(), ps);

    }

    //만약 날짜 변경와 시간변경을 드래그앤 드롭조작으로 한번에할시 먼저 날짜변경호출 후 시간변경 적용


}