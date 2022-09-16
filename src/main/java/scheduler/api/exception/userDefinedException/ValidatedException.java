package scheduler.api.exception.userDefinedException;

import org.springframework.http.HttpStatus;

public class ValidatedException extends Exception{
    private HttpStatus status;

    public ValidatedException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
