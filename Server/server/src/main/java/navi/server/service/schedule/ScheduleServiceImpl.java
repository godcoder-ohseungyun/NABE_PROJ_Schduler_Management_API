package navi.server.service.schedule;

import lombok.RequiredArgsConstructor;
import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;
import navi.server.domain.user.User;
import navi.server.repository.schedule.UserScheduleRepository;
import navi.server.repository.user.UserRepository;
import navi.server.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final UserRepository userRepository;
    private final UserScheduleRepository userScheduleRepository;


    @Override
    public void createPS(User findUser, String date, PersonalSchedule personalSchedule) {
        findUser = userRepository.findById(0l);
        //해당 일자객체 없는경우
        if (findUser.getUserSchedules().get(date) == null) {
            //해당 일자객체 생성
            UserSchedule createUserSchedule = userScheduleRepository.save(new UserSchedule(date));
            // 생성된 객체에 개인일정 추가
            createUserSchedule.getPersonalSchedules().put(personalSchedule.getId(), personalSchedule);
            //유저 객체에 스캐쥴 추가
            findUser.getUserSchedules().put(date, createUserSchedule);

        }
        //해당 일자객체 있는경우
        else {
            //해당 일자객체 조회
            UserSchedule findUserSchedule = findUser.getUserSchedules().get(date);
            //개인일정 추가
            findUserSchedule.getPersonalSchedules().put(personalSchedule.getId(), personalSchedule);
        }

    }

    @Override
    public void createAN(User findUser, String date, AnnouncementSchedule announcementSchedule) {
        //테스트코드
        findUser = userRepository.findById(0l);

        //해당 일자객체 없는경우
        if (findUser.getUserSchedules().get(date) == null) {
            //해당 일자객체 생성
            UserSchedule createUserSchedule = userScheduleRepository.save(new UserSchedule(date));
            // 생성된 객체에 공고일정 추가
            createUserSchedule.getAnnouncementSchedules().put(announcementSchedule.getId(), announcementSchedule);
            //유저 객체에 스캐쥴 추가
            findUser.getUserSchedules().put(date, createUserSchedule);

        }
        //해당 일자객체 있는경우
        else {
            //해당 일자객체 조회
            UserSchedule findUserSchedule = findUser.getUserSchedules().get(date);
            //공고일정 추가
            findUserSchedule.getAnnouncementSchedules().put(announcementSchedule.getId(), announcementSchedule);
        }
    }

    @Override
    public void createSP(User findUser, String date, SpecialSchedule specialSchedule) {
        //테스트코드
        findUser = userRepository.findById(0l);

        //해당 일자객체 없는경우
        if (findUser.getUserSchedules().get(date) == null) {
            //해당 일자객체 생성
            UserSchedule createUserSchedule = userScheduleRepository.save(new UserSchedule(date));
            // 생성된 객체에 개인일정 추가
            createUserSchedule.getSpecialSchedules().put(specialSchedule.getId(), specialSchedule);
            //유저 객체에 스캐쥴 추가
            findUser.getUserSchedules().put(date, createUserSchedule);

        }
        //해당 일자객체 있는경우
        else {
            //해당 일자객체 조회
            UserSchedule findUserSchedule = findUser.getUserSchedules().get(date);
            //개인일정 추가
            findUserSchedule.getSpecialSchedules().put(specialSchedule.getId(), specialSchedule);
        }
    }


    @Override
    public UserSchedule search(User findUser, String date) {
        return findUser.getUserSchedules().get(date);
    }

    @Override
    public Map<String, UserSchedule> searchAll(User findUser) {
        return findUser.getUserSchedules();
    }


    @Override
    public void updateUserPS(User findUser, String date, Long psId , PersonalSchedule personalSchedule) {
        UserSchedule findUserSchedule = search(findUser, date);
        PersonalSchedule findPersonalSchedule = findUserSchedule.getPersonalSchedules().get(psId);

        findPersonalSchedule.setS_time(personalSchedule.getS_time());
        findPersonalSchedule.setE_time(personalSchedule.getE_time());
        findPersonalSchedule.setObservation(personalSchedule.isObservation());
        findPersonalSchedule.setDetail(personalSchedule.getDetail());
    }

    @Override
    public void deletePS(User findUser, String date, Long psId) {
        UserSchedule findUserSchedule = findUser.getUserSchedules().get(date);
        findUserSchedule.getPersonalSchedules().remove(psId);
    }

    @Override
    public void deleteAN(User findUser, String date, Long anId) {
        UserSchedule findUserSchedule = findUser.getUserSchedules().get(date);
        findUserSchedule.getAnnouncementSchedules().remove(anId);
    }

    @Override
    public void deleteSP(User findUser, String date, Long spId) {
        UserSchedule findUserSchedule = findUser.getUserSchedules().get(date);
        findUserSchedule.getSpecialSchedules().remove(spId);
    }


}
