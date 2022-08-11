package scheduler.api.service.an;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scheduler.api.domain.an.AnnouncementSchedule;
import scheduler.api.repository.an.AnnouncementScheduleRepository;

@Service
@Slf4j
@Transactional(readOnly = true)
public class AnnouncementScheduleService {

    @Autowired
    AnnouncementScheduleRepository announcementScheduleRepository;


    /**
     * 이미 존재하는 경우 해당 객체 반환
     * else 생성 후 반환
     */
    @Transactional
    public AnnouncementSchedule getFindOrCreation(AnnouncementSchedule announcementSchedule){

        AnnouncementSchedule find = announcementScheduleRepository.findOneById(announcementSchedule.getId());

        if(find==null){
            return announcementScheduleRepository.save(announcementSchedule);
        }else{
            return find;
        }
    }
}