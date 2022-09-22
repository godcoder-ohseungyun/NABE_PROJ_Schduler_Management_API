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
import scheduler.api.exception.userDefinedException.ValidatedException;
import scheduler.api.rest.RestHeaderCreator;
import scheduler.api.service.ps.PersonalScheduleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @title: 개인 일정 관련 스케줄링 컨트롤러
 * @contents: 사용자가 스스로 작성하는 개인 스케줄 관련 crud를 제공합니다.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonalScheduler {

    private final PersonalScheduleService personalScheduleService;

    private final RestHeaderCreator restHeaderCreator;


    /**
     * @title: 개인 일정 조회
     * @contents: 사용자가 개인 일정을 모두 응답
     */
    @GetMapping("/{memberId}/personal-schedules")
    public ResponseEntity<List<ReadingPersonalScheduleDto>> getPersonalScheduleList(@PathVariable Long memberId) {

        List<ReadingPersonalScheduleDto> PersonalScheduleList = personalScheduleService.findAllByMemberId(memberId);

        return new ResponseEntity<List<ReadingPersonalScheduleDto>>(PersonalScheduleList,
                restHeaderCreator.createRestfulHeader(PersonalSchedule.class,
                        "/{memberId}/personal-schedules",
                        "[POST],[PUT],[DELETE] "),
                HttpStatus.valueOf(200));
    }


    /**
     * @title: 개인 일정 추가
     * @contents: 사용자가 입력한 일정으로 스케줄 생성
     */
    @PostMapping("/{memberId}/personal-schedules")
    public ResponseEntity<String> savePersonalSchedule(@PathVariable Long memberId , @Validated @RequestBody CreatingPersonalScheduleDto creatingPsDto, BindingResult bindingResult) throws ValidatedException {


        isValidated(bindingResult);

        PersonalSchedule newPersonalSchedule
                = PersonalSchedule.createPersonalSchedule(
                creatingPsDto.getTitle(),
                creatingPsDto.getBody(),
                creatingPsDto.getStartTime(),
                creatingPsDto.getEndTime(),
                creatingPsDto.getStartDate(),
                memberId);

        personalScheduleService.save(newPersonalSchedule);


        return new ResponseEntity<String>("",restHeaderCreator.createRestfulHeader(PersonalSchedule.class,
                "/{memberId}/personal-schedules",
                "[GET],[PUT],[DELETE]")
                , HttpStatus.valueOf(200));
    }


     /**
     * @title: 개인 일정 수정
     * @contents:
     * 1. 사용자 스케줄을 수정
     * 2. 단, 내용(Contents)만을 수정하며 이는 title & body 를 의미 합니다.
     */
    @PutMapping("/{memberId}/personal-schedules")
    public ResponseEntity<String> updatePersonalScheduleContents(@Validated @RequestBody UpdatingPersonalScheduleContentsDto updatingPsContentsDto, BindingResult bindingResult) throws ValidatedException {

        isValidated(bindingResult);
        personalScheduleService.update(updatingPsContentsDto);

        return new ResponseEntity<String>("",restHeaderCreator.createRestfulHeader(PersonalSchedule.class,
                "/{memberId}/personal-schedules",
                "[GET],[DELETE]"), HttpStatus.valueOf(200));
    }

    /**
     * @title: 개인 일정 삭제
     * @contents:
     * 1. 사용자 스케줄을 삭제
     * 2. 동시에 여러 스케줄을 삭제할 수 있습니다.
     */
    @DeleteMapping("/{memberId}/personal-schedules")
    public ResponseEntity<String> deletePersonalScheduleList(@RequestBody HashMap<String,Long> personalScheduleIdList){
        personalScheduleService.delete(personalScheduleIdList.values().stream().collect(Collectors.toCollection(ArrayList::new)));

        return new ResponseEntity<String>("",restHeaderCreator.createRestfulHeader(PersonalSchedule.class,
                "/{memberId}/personal-schedules",
                "[GET],[POST]"), HttpStatus.valueOf(200));
    }

    private void isValidated(BindingResult bindingResult) throws ValidatedException {
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.toList()); // 문서화 필요
            throw new ValidatedException(errors.toString(),HttpStatus.BAD_REQUEST);
        }
    }
}
