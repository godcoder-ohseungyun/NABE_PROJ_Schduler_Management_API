package scheduler.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scheduler.api.domain.PersonalSchedule;
import scheduler.api.repository.PersonalScheduleRepository;

import java.util.List;


/**
 * 삽입 - 단일일정 삽입 후 id 반환
 * 삭제 - 다수일정 동시 삭제 가능
 * 수정 - 단일일정 제목,내용 수정
 * 읽기 - 전체 조회 , *특정 조건 조회
 */
@Service
@Transactional(readOnly = true)
public class PersonalScheduleService {

    @Autowired
    PersonalScheduleRepository personalScheduleRepository;

    @Transactional
    public Long save(PersonalSchedule personalSchedule){
        personalScheduleRepository.save(personalSchedule);
        return personalSchedule.getId();
    }

    @Transactional
    public void delete(Long... psIds){

        for(Long id : psIds){
            personalScheduleRepository.delete(id);
        }

    }

    @Transactional
    public List<PersonalSchedule> findAll(Long memberId){
        return personalScheduleRepository.findAll(memberId);
    }

    @Transactional
    public void update(Long psId,String title,String body){
        personalScheduleRepository.update(psId,title,body);
    }

}
