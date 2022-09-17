package scheduler.api.exception.userDefinedException;

import org.springframework.http.HttpStatus;

public class AuthenticationImpossibleException extends Exception{
    private HttpStatus status;

    public AuthenticationImpossibleException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
