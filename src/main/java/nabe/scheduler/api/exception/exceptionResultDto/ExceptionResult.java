package nabe.scheduler.api.exception.exceptionResultDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResult {
    private Date timeStamp; //yyyy-mm-dd
    private String code; //error-title
    private String message; //error-message
    private String details; //error-position
}
