package scheduler.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import scheduler.api.domain.an.AnnouncementSchedule;
import scheduler.api.dto.an.AnnouncementDto;
import scheduler.api.dto.an.ReadingAsubDto;
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
    public void subscribe(@PathVariable Long memberId, @RequestBody AnnouncementDto announcementDto) {

        AnnouncementSchedule newAnnouncementSchedule = new AnnouncementSchedule(announcementDto.getId(),
                announcementDto.getTitle(),
                announcementDto.getOriginalUrl(),
                announcementDto.getStartDate(),
                announcementDto.getEndDate());

        announcementSupscriptionService.subscribe(memberId, newAnnouncementSchedule);

    }

    @DeleteMapping("/{memberId}/announcement-schedules")
    public void cancelSubscribe(@RequestBody HashMap<String, Long> request) {
        announcementSupscriptionService.cancelSubscribe(request.values().stream().collect(Collectors.toCollection(ArrayList::new)));
    }

    @GetMapping("/{memberId}/announcement-schedules")
    public List<ReadingAsubDto> readAnnounceSchedule(@PathVariable Long memberId){
        return announcementSupscriptionService.findAll(memberId);
    }
}
