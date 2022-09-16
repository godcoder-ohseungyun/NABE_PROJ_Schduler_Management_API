package scheduler.api.exception.exceptionController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import scheduler.api.exception.userDefinedException.ValidatedException;
import scheduler.api.exception.exceptionResultDto.ExceptionResult;
import scheduler.api.exception.userDefinedException.DuplicateDataException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@RestControllerAdvice
public class ExceptionController  {

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ExceptionResult> DpEx(DuplicateDataException e , HttpServletRequest request){
        ExceptionResult exceptionResult = new ExceptionResult(new Date(),
                "Duplicate-Data-Denied",
                e.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<>(exceptionResult,e.getStatus());
    }

    @ExceptionHandler(ValidatedException.class)
    public ResponseEntity<ExceptionResult> VlEx(ValidatedException e , HttpServletRequest request) {
        ExceptionResult exceptionResult = new ExceptionResult(new Date(),
                "Wrong-Validated",
                e.getMessage(),
                request.getRequestURI());


        return new ResponseEntity<>(exceptionResult,e.getStatus());
    }
}
