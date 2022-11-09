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
 * @detail
 * - 사용자의 개인일정 조회,추가,수정,삭제 기능을 제공합니다.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonalScheduler {

    private final PersonalScheduleService personalScheduleService;

    private final RestHeaderCreator restHeaderCreator;


    @GetMapping("/{memberId}/personal-schedules")
    public ResponseEntity<List<ReadingPersonalScheduleDto>> getPersonalScheduleList(@PathVariable Long memberId) {

        List<ReadingPersonalScheduleDto> PersonalScheduleList = personalScheduleService.findAllByMemberId(memberId);

        return new ResponseEntity<List<ReadingPersonalScheduleDto>>(PersonalScheduleList,
                restHeaderCreator.createRestfulHeader(PersonalSchedule.class,
                        "/{memberId}/personal-schedules",
                        "[POST],[PUT],[DELETE] "),
                HttpStatus.valueOf(200));
    }

    //입력 일정 내용이 동일해도 별도로 구분하며, 중복 생성 합니다.
    @PostMapping("/{memberId}/personal-schedules")
    public ResponseEntity<String> savePersonalSchedule(@PathVariable Long memberId
            , @Validated @RequestBody CreatingPersonalScheduleDto creatingPsDto
            , BindingResult bindingResult) throws ValidatedException {


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


        return new ResponseEntity<String>("", restHeaderCreator.createRestfulHeader(PersonalSchedule.class,
                "/{memberId}/personal-schedules",
                "[GET],[PUT],[DELETE]")
                , HttpStatus.valueOf(200));
    }


    //내용(Contents)만을 수정하며 이는 title & body 를 의미 합니다.
    @PutMapping("/{memberId}/personal-schedules")
    public ResponseEntity<String> updatePersonalScheduleContents(
            @Validated @RequestBody UpdatingPersonalScheduleContentsDto updatingPsContentsDto
            , BindingResult bindingResult) throws ValidatedException {

        isValidated(bindingResult);
        personalScheduleService.update(updatingPsContentsDto);

        return new ResponseEntity<String>("", restHeaderCreator.createRestfulHeader(PersonalSchedule.class,
                "/{memberId}/personal-schedules",
                "[GET],[DELETE]"), HttpStatus.valueOf(200));
    }

    //@RequestBody: '가변 json'을 이용해 가변적으로 요청된 모든 id값 제거
    @DeleteMapping("/{memberId}/personal-schedules")
    public ResponseEntity<String> deletePersonalScheduleList(@RequestBody HashMap<String, Long> personalScheduleIdList) {
        personalScheduleService.delete(personalScheduleIdList
                .values()
                .stream()
                .collect(Collectors.toCollection(ArrayList::new)));

        return new ResponseEntity<String>("", restHeaderCreator.createRestfulHeader(PersonalSchedule.class,
                "/{memberId}/personal-schedules",
                "[GET],[POST]"), HttpStatus.valueOf(200));
    }

    private void isValidated(BindingResult bindingResult) throws ValidatedException {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList()); // 문서화 필요

            throw new ValidatedException(errors.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
