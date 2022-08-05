package scheduler.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import scheduler.api.domain.an.AnnouncementSchedule;
import scheduler.api.dto.an.CreatingAnnouncementDto;
import scheduler.api.dto.an.AnnouncementSubscriptionDto;
import scheduler.api.exception.exceptionDomain.DuplicateDataException;
import scheduler.api.exception.exceptionDomain.ValidatedException;
import scheduler.api.service.an.AnnouncementSupscriptionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnnouncementScheduler {

    private final AnnouncementSupscriptionService announcementSupscriptionService;


    @PostMapping("/{memberId}/announcement-schedules")
    public void subscribe(@PathVariable Long memberId, @Validated @RequestBody CreatingAnnouncementDto creatingAnnouncementDto , BindingResult bindingResult) throws DuplicateDataException, ValidatedException {

        isValidated(bindingResult);

        //준영속 entity?
        AnnouncementSchedule newAnnouncementSchedule = new AnnouncementSchedule(creatingAnnouncementDto.getId(),
                creatingAnnouncementDto.getTitle(),
                creatingAnnouncementDto.getOriginalUrl(),
                creatingAnnouncementDto.getStartDate(),
                creatingAnnouncementDto.getEndDate());

        announcementSupscriptionService.subscribe(memberId, newAnnouncementSchedule);

    }

    @DeleteMapping("/{memberId}/announcement-schedules")
    public void cancelSubscribeList(@RequestBody HashMap<String, Long> announcementSubscriptionIdList) {
        announcementSupscriptionService.cancelSubscribe(announcementSubscriptionIdList.values().stream().collect(Collectors.toCollection(ArrayList::new)));
    }

    @GetMapping("/{memberId}/announcement-schedules")
    public List<AnnouncementSubscriptionDto> getAnnounceScheduleList(@PathVariable Long memberId){
        return announcementSupscriptionService.findAllByMemberId(memberId);
    }

    private void isValidated(BindingResult bindingResult) throws ValidatedException {
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.toList()); // 문서화 필요
            throw new ValidatedException(errors.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
