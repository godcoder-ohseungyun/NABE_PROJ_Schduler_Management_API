package scheduler.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scheduler.api.domain.PersonalSchedule;
import scheduler.api.dto.CreatingPsDto;
import scheduler.api.dto.UpdatingPsContentsDto;
import scheduler.api.service.PersonalScheduleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonalScheduer {

    private final PersonalScheduleService personalScheduleService;

    @GetMapping("/{memberId}/personal-schedules")
    public ResponseEntity<List<PersonalSchedule>> getPersonalSchedules(@PathVariable Long memberId) {

        List<PersonalSchedule> list = personalScheduleService.findAll(memberId);

        return new ResponseEntity<List<PersonalSchedule>>(list,null, HttpStatus.valueOf(200));
    }


    @PostMapping("/{memberId}/personal-schedules")
    public ResponseEntity<String> savePersonalSchedule(@PathVariable Long memberId ,@RequestBody CreatingPsDto request){

        //TODO
        //중복 저장 check
        //소유자 정보 null check
        PersonalSchedule newPersonalSchedule
                = new PersonalSchedule(
                request.getTitle(),
                request.getBody(),
                request.getStartTime(),
                request.getEndTime(),
                request.getDate(),
                memberId);

        personalScheduleService.save(newPersonalSchedule);


        return null;
    }

    @PutMapping("/{memberId}/personal-schedules")
    public void updatePersonalSchedule(@PathVariable Long memberId , @RequestBody UpdatingPsContentsDto request){
        personalScheduleService.update(request.getPsId(),request.getTitle(),request.getBody());
    }


    @DeleteMapping("/{memberId}/personal-schedules")
    public void deletePersonalSchedules(@PathVariable Long memberId , @RequestBody HashMap<String,Long> request){
        personalScheduleService.delete(request.values().stream().collect(Collectors.toCollection(ArrayList::new)));
    }
}
