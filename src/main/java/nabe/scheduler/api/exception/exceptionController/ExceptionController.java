package nabe.scheduler.api.exception.exceptionController;

import lombok.extern.slf4j.Slf4j;
import nabe.scheduler.api.exception.exceptionResultDto.ExceptionResult;
import nabe.scheduler.api.exception.definedException.ValidatedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@RestControllerAdvice
public class ExceptionController  {

    @ExceptionHandler(ValidatedException.class)
    public ResponseEntity<ExceptionResult> VlEx(ValidatedException e , HttpServletRequest request) {
        ExceptionResult exceptionResult = new ExceptionResult(new Date(),
                "Wrong-Validated",
                e.getMessage(),
                request.getRequestURI());


        return new ResponseEntity<>(exceptionResult,e.getStatus());
    }
}
