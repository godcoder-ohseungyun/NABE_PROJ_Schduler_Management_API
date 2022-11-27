package nabe.scheduler.api.controller;

import lombok.RequiredArgsConstructor;
import nabe.scheduler.api.domain.PersonalSchedule;
import nabe.scheduler.api.dto.CreatingPersonalScheduleDto;
import nabe.scheduler.api.dto.ReadingPersonalScheduleDto;
import nabe.scheduler.api.dto.UpdatingPersonalScheduleContentsDto;
import nabe.scheduler.api.exception.definedException.ValidatedException;
import nabe.scheduler.api.rest.RestHeaderCreator;
import nabe.scheduler.api.service.PersonalScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonalScheduleController {

    private final PersonalScheduleService personalScheduleService;


    @GetMapping("/{memberId}/personal-schedules")
    public ResponseEntity<List<ReadingPersonalScheduleDto>> getPersonalScheduleList
            (@PathVariable Long memberId) {

        List<ReadingPersonalScheduleDto> PersonalScheduleList = personalScheduleService.findAllByMemberId(memberId);

        return new ResponseEntity<List<ReadingPersonalScheduleDto>>(
                PersonalScheduleList
                , RestHeaderCreator.createRestfulHeader(
                        PersonalSchedule.class
                        , "/{memberId}/personal-schedules"
                        , "[POST],[PUT],[DELETE] "
                )
                , HttpStatus.valueOf(200)
        );
    }

    @PostMapping("/{memberId}/personal-schedules")
    public ResponseEntity<String> savePersonalSchedule(@PathVariable Long memberId
            , @Validated @RequestBody CreatingPersonalScheduleDto creatingPsDto
            , BindingResult bindingResult) {


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


        return new ResponseEntity<String>(
                ""
                , RestHeaderCreator.createRestfulHeader(
                        PersonalSchedule.class
                        , "/{memberId}/personal-schedules"
                        , "[GET],[PUT],[DELETE]"
                )
                , HttpStatus.valueOf(200));
    }


    @PutMapping("/{memberId}/personal-schedules")
    public ResponseEntity<String> updatePersonalScheduleContents(
            @Validated @RequestBody UpdatingPersonalScheduleContentsDto updatingPsContentsDto
            , BindingResult bindingResult){

        isValidated(bindingResult);
        personalScheduleService.update(updatingPsContentsDto);

        return new ResponseEntity<String>(
                ""
                , RestHeaderCreator.createRestfulHeader(
                        PersonalSchedule.class
                        ,"/{memberId}/personal-schedules"
                        , "[GET],[DELETE]"
                )
                , HttpStatus.valueOf(200));
    }

    @DeleteMapping("/{memberId}/personal-schedules")
    public ResponseEntity<String> deletePersonalScheduleList(
            @RequestBody HashMap<String, Long> personalScheduleIdList) {

        personalScheduleService.deleteThese(personalScheduleIdList
                .values()
                .stream()
                .collect(Collectors.toCollection(ArrayList::new)));

        return new ResponseEntity<String>(
                ""
                , RestHeaderCreator.createRestfulHeader(
                        PersonalSchedule.class
                    , "/{memberId}/personal-schedules"
                    , "[GET],[POST]"
                )
                , HttpStatus.valueOf(200));
    }

    private void isValidated(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());

            throw new ValidatedException(errors.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
