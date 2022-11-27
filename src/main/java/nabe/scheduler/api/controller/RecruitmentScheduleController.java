package nabe.scheduler.api.controller;

import lombok.RequiredArgsConstructor;
import nabe.scheduler.api.domain.RecruitmentSchedule;
import nabe.scheduler.api.dto.CreatingRecruitmentScheduleDto;
import nabe.scheduler.api.dto.ReadingRecruitmentScheduleSubscriptionDto;
import nabe.scheduler.api.exception.definedException.ValidatedException;
import nabe.scheduler.api.rest.RestHeaderCreator;
import nabe.scheduler.api.service.RecruitmentScheduleSubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class RecruitmentScheduleController {

    private final RecruitmentScheduleSubscriptionService recruitmentScheduleSubscriptionService;
    private final RestHeaderCreator restHeaderCreator;

    @PostMapping("/{memberId}/recruitment-schedules")
    public ResponseEntity<String> subscribe(@PathVariable Long memberId
            , @Validated @RequestBody CreatingRecruitmentScheduleDto creatingRecruitmentScheduleDto
            , BindingResult bindingResult){

        isValidated(bindingResult);

        RecruitmentSchedule createdRecruitmentSchedule = RecruitmentSchedule
                .createRecruitmentSchedule(
                        creatingRecruitmentScheduleDto.getId(),
                        creatingRecruitmentScheduleDto.getTitle(),
                        creatingRecruitmentScheduleDto.getOriginalUrl(),
                        creatingRecruitmentScheduleDto.getStartDate(),
                        creatingRecruitmentScheduleDto.getEndDate()
                );

        recruitmentScheduleSubscriptionService.subscribe(memberId, createdRecruitmentSchedule);

        return new ResponseEntity<>(
                ""
                , restHeaderCreator.createRestfulHeader(
                        RecruitmentScheduleController.class
                        , "/{memberId}/recruitment-schedules"
                        , "[GET],[DELETE]"
                )
                , HttpStatus.valueOf(200));

    }

    //@RequestBody: '가변 json'을 이용해 가변적으로 요청된 모든 id값 제거
    @DeleteMapping("/{memberId}/recruitment-schedules")
    public ResponseEntity<String> cancelSubscribes(@RequestBody HashMap<String, Long> recruitmentScheduleSubscriptionIds) {

        recruitmentScheduleSubscriptionService.cancelSubscribe(recruitmentScheduleSubscriptionIds
                .values()
                .stream()
                .collect(Collectors.toCollection(ArrayList::new)));

        return new ResponseEntity<>(
                ""
                , restHeaderCreator.createRestfulHeader(
                        RecruitmentScheduleController.class
                        , "/{memberId}/recruitment-schedules"
                        , "[GET],[POST]"
                )
                , HttpStatus.valueOf(200));
    }

    @GetMapping("/{memberId}/recruitment-schedules")
    public ResponseEntity<List<ReadingRecruitmentScheduleSubscriptionDto>> getRecruitmentSchedules(
            @PathVariable Long memberId) {

        List<ReadingRecruitmentScheduleSubscriptionDto> findlist
                = recruitmentScheduleSubscriptionService.findAllByMemberId(memberId);

        return new ResponseEntity<>(
                findlist
                , restHeaderCreator.createRestfulHeader(
                        RecruitmentScheduleController.class
                        , "/{memberId}/recruitment-schedules"
                        , "[POST],[DELETE]"
                )
                , HttpStatus.valueOf(200));
    }

    private void isValidated(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult
                    .getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());
            
            throw new ValidatedException(errors.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
