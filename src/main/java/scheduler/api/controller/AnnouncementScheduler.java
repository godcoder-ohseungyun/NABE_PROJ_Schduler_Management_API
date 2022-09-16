package scheduler.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import scheduler.api.domain.an.AnnouncementSchedule;
import scheduler.api.dto.an.CreatingAnnouncementDto;
import scheduler.api.dto.an.ReadingAnnouncementSubscriptionDto;
import scheduler.api.exception.userDefinedException.DuplicateDataException;
import scheduler.api.exception.userDefinedException.ValidatedException;
import scheduler.api.service.an.AnnouncementSupscriptionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @title: 공고 일정 관련 스케줄링 컨트롤러
 * @contents: 서비스에서 조회한 '채용 및 스팩 활동 공고'를 구독 , 구독취소 등 의 기능을 구현 합니다.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnnouncementScheduler {

    private final AnnouncementSupscriptionService announcementSupscriptionService;


    /**
     * @title: 공고 구독
     * @contents:
     * 1. 구독한 공고로 사용자 스케줄을 자동 생성
     * 2. 사용자가 해당 공고를 구독했다는 정보를 저장
     */
    @PostMapping("/{memberId}/announcement-schedules")
    public ResponseEntity<String> subscribe(@PathVariable Long memberId, @Validated @RequestBody CreatingAnnouncementDto creatingAnnouncementDto , BindingResult bindingResult) throws DuplicateDataException, ValidatedException {

        isValidated(bindingResult);

        AnnouncementSchedule newAnnouncementSchedule = AnnouncementSchedule.createAnnouncementSchedule(creatingAnnouncementDto.getId(),
                creatingAnnouncementDto.getTitle(),
                creatingAnnouncementDto.getOriginalUrl(),
                creatingAnnouncementDto.getStartDate(),
                creatingAnnouncementDto.getEndDate());

        announcementSupscriptionService.subscribe(memberId, newAnnouncementSchedule);

        return new ResponseEntity<String>("",null, HttpStatus.valueOf(200));

    }

    /**
     * @title: 구독 취소
     * @contents:
     * 1. 구독취소한 공고를 사용자 스케줄에서 제거
     * 2. 사용자가 해당 공고를 구독했다는 정보를 제거
     */
    @DeleteMapping("/{memberId}/announcement-schedules")
    public ResponseEntity<String> cancelSubscribeList(@RequestBody HashMap<String, Long> announcementSubscriptionIdList) {
        announcementSupscriptionService.cancelSubscribe(announcementSubscriptionIdList.values().stream().collect(Collectors.toCollection(ArrayList::new)));
        return new ResponseEntity<String>("",null, HttpStatus.valueOf(200));
    }

    /**
     * @title: 구독 조회
     * @contents:
     * 2. 사용자가 구독한 모든 공고를 응답
     */
    @GetMapping("/{memberId}/announcement-schedules")
    public List<ReadingAnnouncementSubscriptionDto> getAnnounceScheduleList(@PathVariable Long memberId){
        return announcementSupscriptionService.findAllByMemberId(memberId);
    }

    private void isValidated(BindingResult bindingResult) throws ValidatedException {
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.toList()); // 문서화 필요
            throw new ValidatedException(errors.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
