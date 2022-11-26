package nabe.scheduler.api.exception.definedException;

import org.springframework.http.HttpStatus;

public class ValidatedException extends RuntimeException{
    private HttpStatus status;

    public ValidatedException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
