package scheduler.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import scheduler.api.domain.ps.PersonalSchedule;
import scheduler.api.dto.ps.CreatingPsDto;
import scheduler.api.dto.ps.UpdatingPsContentsDto;
import scheduler.api.exception.exceptionDomain.ValidatedException;
import scheduler.api.service.ps.PersonalScheduleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonalScheduler {

    private final PersonalScheduleService personalScheduleService;

    @GetMapping("/{memberId}/personal-schedules")
    public ResponseEntity<List<PersonalSchedule>> getPersonalSchedules(@PathVariable Long memberId) {

        List<PersonalSchedule> list = personalScheduleService.findAll(memberId);

        return new ResponseEntity<List<PersonalSchedule>>(list,null, HttpStatus.valueOf(200));
    }


    @PostMapping("/{memberId}/personal-schedules")
    public ResponseEntity<String> savePersonalSchedule(@PathVariable Long memberId , @Validated @RequestBody CreatingPsDto request, BindingResult bindingResult) throws ValidatedException {


        isValidated(bindingResult);

        PersonalSchedule newPersonalSchedule
                = new PersonalSchedule(
                request.getTitle(),
                request.getBody(),
                request.getStartTime(),
                request.getEndTime(),
                request.getDate(),
                memberId);

        personalScheduleService.save(newPersonalSchedule);


        return null;
    }

    @PutMapping("/{memberId}/personal-schedules")
    public void updatePersonalSchedule(@PathVariable Long memberId , @Validated @RequestBody UpdatingPsContentsDto request, BindingResult bindingResult) throws ValidatedException {
        isValidated(bindingResult);
        personalScheduleService.update(request);
    }


    @DeleteMapping("/{memberId}/personal-schedules")
    public void deletePersonalSchedules(@PathVariable Long memberId , @RequestBody HashMap<String,Long> request){
        personalScheduleService.delete(request.values().stream().collect(Collectors.toCollection(ArrayList::new)));
    }

    private void isValidated(BindingResult bindingResult) throws ValidatedException {
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.toList()); // 문서화 필요
            throw new ValidatedException(errors.toString(),HttpStatus.BAD_REQUEST);
        }
    }
}
