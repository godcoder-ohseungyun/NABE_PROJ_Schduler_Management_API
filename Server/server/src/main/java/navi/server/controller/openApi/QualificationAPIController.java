package navi.server.controller.openApi;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import navi.server.domain.schedule.Exam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class QualificationAPIController {

    @GetMapping("/qualification-exams")
    public List<Exam> getQualificationExams(){

        
        return null;
    }
}
