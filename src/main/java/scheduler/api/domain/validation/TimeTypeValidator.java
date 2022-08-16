package scheduler.api.domain.validation;

import org.springframework.http.HttpStatus;
import scheduler.api.exception.exceptionDomain.ValidatedException;

public class TimeTypeValidator implements Validator{
    private final static int MAX_TIME = 2400;
    private final static int MIN_TIME = 0;

    private final static String ERROR_MSG = "잘못된 양식의 시간 입니다.";
    @Override
    public void validate(String field) throws ValidatedException {
        int numTypeFromStartDate = Integer.parseInt(field);

        if (field.length() != 4 ||
                (numTypeFromStartDate <= MIN_TIME && numTypeFromStartDate >= MAX_TIME))
            throw new ValidatedException(ERROR_MSG, HttpStatus.BAD_REQUEST);
    }
}
