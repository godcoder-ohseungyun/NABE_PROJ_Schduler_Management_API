package scheduler.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import scheduler.api.domain.an.AnnouncementSchedule;
import scheduler.api.dto.an.AnnouncementDto;
import scheduler.api.dto.an.ReadingAsubDto;
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
    public void subscribe(@PathVariable Long memberId, @Validated @RequestBody AnnouncementDto announcementDto , BindingResult bindingResult) throws DuplicateDataException, ValidatedException {

        isValidated(bindingResult);

        //준영속 entity
        AnnouncementSchedule newAnnouncementSchedule = new AnnouncementSchedule(announcementDto.getId(),
                announcementDto.getTitle(),
                announcementDto.getOriginalUrl(),
                announcementDto.getStartDate(),
                announcementDto.getEndDate());

        announcementSupscriptionService.subscribe(memberId, newAnnouncementSchedule);
        //https://thatisgood.tistory.com/entry/JPA-Referential-integrity-constraint-violation-오류
    }

    @DeleteMapping("/{memberId}/announcement-schedules")
    public void cancelSubscribe(@RequestBody HashMap<String, Long> request) {
        announcementSupscriptionService.cancelSubscribe(request.values().stream().collect(Collectors.toCollection(ArrayList::new)));
    }

    @GetMapping("/{memberId}/announcement-schedules")
    public List<ReadingAsubDto> readAnnounceSchedule(@PathVariable Long memberId){
        return announcementSupscriptionService.findAll(memberId);
    }

    private void isValidated(BindingResult bindingResult) throws ValidatedException {
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.toList()); // 문서화 필요
            throw new ValidatedException(errors.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
