package scheduler.api.exception.exceptionResultDto;

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
    private Date timeStamp; //예외 발생 시간 yyyy mm dd
    private String code; //예외 제목(발생 원인)
    private String message; //예외 발생시 전달한 메세지
    private String details; //예외 발생 위치
}
