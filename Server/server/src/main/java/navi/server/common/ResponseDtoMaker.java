package navi.server.common;

import navi.server.domain.schedule.UserSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.AnnouncementSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.PersonalSchedule;
import navi.server.domain.schedule.userScheduleSubclasses.SpecialSchedule;
import navi.server.domain.user.User;
import navi.server.dto.responseDTO.PersonalSchduleResponseDTO;
import navi.server.dto.responseDTO.UserResponseDTO;
import navi.server.dto.responseDTO.UserScheduleResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Map 자료구조를 필드에 가지는 응답을 List 형태로 가지도록 변환하는 작업
 * Json 양식을 정형화 하기위해서 사용합니다.
 *
 * 관련: UserResponseDTO
 */
@Component
public class ResponseDtoMaker {

    /**
     * user 도메인의 Map 필드를 List로 변환해 정돈된 json 형태로 변환
     */
    public UserResponseDTO convertToUserResponseDTO(User user){


        List<UserScheduleResponseDTO> userScheduleResponseDTOS = new LinkedList<>();


        ArrayList<UserSchedule> ulist = new ArrayList<>(user.getUserSchedules().values());


        for(UserSchedule userSchedule : ulist){

            ArrayList<PersonalSchedule> plist = new ArrayList<>(userSchedule.getPersonalSchedules().values());

            List<PersonalSchduleResponseDTO> personalSchduleResponseDTOS = new LinkedList<>();

            for(PersonalSchedule personalSchedule: plist){
                personalSchduleResponseDTOS.add(new PersonalSchduleResponseDTO(personalSchedule.getId(),
                        personalSchedule.getS_time(),
                        personalSchedule.getE_time(),
                        personalSchedule.isObservation(),
                        personalSchedule.getDetail()));
            }


            UserScheduleResponseDTO userScheduleResponseDTO = new UserScheduleResponseDTO(userSchedule.getId(),userSchedule.getDate(), personalSchduleResponseDTOS);

            userScheduleResponseDTOS.add(userScheduleResponseDTO);

        }

        return new UserResponseDTO(
                user.getId(),
                user.getPassword(),
                user.getEmail(),
                user.getName(),
                new ArrayList<>(user.getAnnouncementSchedules().values()),
                new ArrayList<>(user.getSpecialSchedules().values()),
                userScheduleResponseDTOS);
    }

    /**
     * AnnouncementSchedule 도메인의 Map 필드를 List로 변환해 정돈된 json 형태로 변환
     */
    public List<AnnouncementSchedule> convertToAnnouncementResponseDTO(User user){
        return new ArrayList<>(user.getAnnouncementSchedules().values());
    }

    /**
     * SpecialSchedule 도메인의 Map 필드를 List로 변환해 정돈된 json 형태로 변환
     */
    public List<SpecialSchedule> convertToSpecialResponseDTO(User user){
        return new ArrayList<>(user.getSpecialSchedules().values());
    }

    /**
     * userSchedule 도메인의 Map 필드를 List로 변환해 정돈된 json 형태로 변환
     */
    public List<UserScheduleResponseDTO> convertToUserScheduleResponseDTO(User user){
        List<UserScheduleResponseDTO> userScheduleResponseDTOS = new LinkedList<>();

        ArrayList<UserSchedule> ulist = new ArrayList<>(user.getUserSchedules().values());

        for(UserSchedule userSchedule : ulist){

            ArrayList<PersonalSchedule> plist = new ArrayList<>(userSchedule.getPersonalSchedules().values());

            List<PersonalSchduleResponseDTO> personalSchduleResponseDTOS = new LinkedList<>();

            for(PersonalSchedule personalSchedule: plist){
                personalSchduleResponseDTOS.add(new PersonalSchduleResponseDTO(personalSchedule.getId(),
                        personalSchedule.getS_time(),
                        personalSchedule.getE_time(),
                        personalSchedule.isObservation(),
                        personalSchedule.getDetail()));
            }

            UserScheduleResponseDTO userScheduleResponseDTO = new UserScheduleResponseDTO(userSchedule.getId(),userSchedule.getDate(), personalSchduleResponseDTOS);

            userScheduleResponseDTOS.add(userScheduleResponseDTO);
        }
        return userScheduleResponseDTOS;

    }
}
