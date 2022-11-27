package nabe.scheduler.api.exception.definedException;

import org.springframework.http.HttpStatus;

public class DuplicateDataException extends RuntimeException {

    private HttpStatus status;

    public DuplicateDataException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
