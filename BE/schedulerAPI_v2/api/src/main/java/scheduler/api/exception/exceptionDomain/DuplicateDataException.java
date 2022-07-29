package scheduler.api.exception.exceptionDomain;

import org.springframework.http.HttpStatus;

//checked 예외임
public class DuplicateDataException extends Exception {

    private HttpStatus status;

    public DuplicateDataException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
