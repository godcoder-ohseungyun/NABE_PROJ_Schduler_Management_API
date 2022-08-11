package scheduler.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import scheduler.api.domain.ps.PersonalSchedule;
import scheduler.api.dto.ps.CreatingPersonalScheduleDto;
import scheduler.api.dto.ps.ReadingPersonalScheduleDto;
import scheduler.api.dto.ps.UpdatingPersonalScheduleContentsDto;
import scheduler.api.exception.exceptionDomain.ValidatedException;
import scheduler.api.service.ps.PersonalScheduleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonalScheduler {

    private final PersonalScheduleService personalScheduleService;

    @GetMapping("/{memberId}/personal-schedules")
    public ResponseEntity<List<ReadingPersonalScheduleDto>> getPersonalScheduleList(@PathVariable Long memberId) {

        List<ReadingPersonalScheduleDto> PersonalScheduleList = personalScheduleService.findAllByMemberId(memberId);

        return new ResponseEntity<List<ReadingPersonalScheduleDto>>(PersonalScheduleList,null, HttpStatus.valueOf(200));
    }



    @PostMapping("/{memberId}/personal-schedules")
    public ResponseEntity<String> savePersonalSchedule(@PathVariable Long memberId , @Validated @RequestBody CreatingPersonalScheduleDto creatingPsDto, BindingResult bindingResult) throws ValidatedException {


        isValidated(bindingResult);

        PersonalSchedule newPersonalSchedule
                = PersonalSchedule.createPersonalSchedule(
                creatingPsDto.getTitle(),
                creatingPsDto.getBody(),
                creatingPsDto.getStartTime(),
                creatingPsDto.getEndTime(),
                creatingPsDto.getDate(),
                memberId);

        personalScheduleService.save(newPersonalSchedule);


        return new ResponseEntity<String>("",null, HttpStatus.valueOf(200));
    }

    /**
     * Contents는 title & body 를 의미 합니다.
     */
    @PutMapping("/{memberId}/personal-schedules")
    public ResponseEntity<String> updatePersonalScheduleContents(@Validated @RequestBody UpdatingPersonalScheduleContentsDto updatingPsContentsDto, BindingResult bindingResult) throws ValidatedException {

        isValidated(bindingResult);
        personalScheduleService.update(updatingPsContentsDto);

        return new ResponseEntity<String>("",null, HttpStatus.valueOf(200));
    }


    @DeleteMapping("/{memberId}/personal-schedules")
    public ResponseEntity<String> deletePersonalScheduleList(@RequestBody HashMap<String,Long> personalScheduleIdList){
        personalScheduleService.delete(personalScheduleIdList.values().stream().collect(Collectors.toCollection(ArrayList::new)));

        return new ResponseEntity<String>("",null, HttpStatus.valueOf(200));
    }

    private void isValidated(BindingResult bindingResult) throws ValidatedException {
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.toList()); // 문서화 필요
            throw new ValidatedException(errors.toString(),HttpStatus.BAD_REQUEST);
        }
    }
}
