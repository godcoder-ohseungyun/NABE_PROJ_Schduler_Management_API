package scheduler.api.exception.exceptionDto;

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
    private Date timeStamp;
    private String code;
    private String message;
    private String details;
}
