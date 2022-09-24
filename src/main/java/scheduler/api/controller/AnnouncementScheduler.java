package scheduler.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import scheduler.api.domain.an.AnnouncementSchedule;
import scheduler.api.domain.ps.PersonalSchedule;
import scheduler.api.dto.an.CreatingAnnouncementDto;
import scheduler.api.dto.an.ReadingAnnouncementSubscriptionDto;
import scheduler.api.exception.userDefinedException.DuplicateDataException;
import scheduler.api.exception.userDefinedException.ValidatedException;
import scheduler.api.rest.RestHeaderCreator;
import scheduler.api.service.an.AnnouncementSupscriptionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @detail - 서비스에서 제공한 채용 및 스팩 활동 공고를 '구독' , '구독취소' 기능을 제공합니다.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnnouncementScheduler {

    private final AnnouncementSupscriptionService announcementSupscriptionService;

    private final RestHeaderCreator restHeaderCreator;

    @PostMapping("/{memberId}/announcement-schedules")
    public ResponseEntity<String> subscribe(@PathVariable Long memberId, @Validated @RequestBody CreatingAnnouncementDto creatingAnnouncementDto, BindingResult bindingResult) throws DuplicateDataException, ValidatedException {

        isValidated(bindingResult);

        AnnouncementSchedule newAnnouncementSchedule = AnnouncementSchedule.createAnnouncementSchedule(creatingAnnouncementDto.getId(),
                creatingAnnouncementDto.getTitle(),
                creatingAnnouncementDto.getOriginalUrl(),
                creatingAnnouncementDto.getStartDate(),
                creatingAnnouncementDto.getEndDate());

        announcementSupscriptionService.subscribe(memberId, newAnnouncementSchedule);

        return new ResponseEntity<String>("", restHeaderCreator.createRestfulHeader(AnnouncementScheduler.class,
                "/{memberId}/announcement-schedules",
                "[GET],[DELETE]"), HttpStatus.valueOf(200));

    }

    //@RequestBody: '가변 json'을 이용해 가변적으로 요청된 모든 id값 제거
    @DeleteMapping("/{memberId}/announcement-schedules")
    public ResponseEntity<String> cancelSubscribeList(@RequestBody HashMap<String, Long> announcementSubscriptionIdList) {
        announcementSupscriptionService.cancelSubscribe(announcementSubscriptionIdList.values().stream().collect(Collectors.toCollection(ArrayList::new)));
        return new ResponseEntity<String>("", restHeaderCreator.createRestfulHeader(AnnouncementScheduler.class,
                "/{memberId}/announcement-schedules",
                "[GET],[POST]"), HttpStatus.valueOf(200));
    }

    @GetMapping("/{memberId}/announcement-schedules")
    public ResponseEntity<List<ReadingAnnouncementSubscriptionDto>> getAnnounceScheduleList(@PathVariable Long memberId) {

        List<ReadingAnnouncementSubscriptionDto> findlist = announcementSupscriptionService.findAllByMemberId(memberId);

        return new ResponseEntity<List<ReadingAnnouncementSubscriptionDto>>(findlist, restHeaderCreator.createRestfulHeader(AnnouncementScheduler.class,
                "/{memberId}/announcement-schedules",
                "[POST],[DELETE]"), HttpStatus.valueOf(200));
    }

    private void isValidated(BindingResult bindingResult) throws ValidatedException {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()); // 문서화 필요
            throw new ValidatedException(errors.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
