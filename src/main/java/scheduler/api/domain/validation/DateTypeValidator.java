package scheduler.api.domain.validation;

import org.springframework.http.HttpStatus;
import scheduler.api.exception.exceptionDomain.ValidatedException;

public class DateTypeValidator implements Validator{

    private final static int MAX_DATE = 20501231;

    private final static String ERROR_MSG = "잘못된 양식의 날짜 입니다.";
    @Override
    public void validate(String field) throws ValidatedException {
        int numTypeFromStartDate = Integer.parseInt(field);

        if (field.length() != 8 ||
                (numTypeFromStartDate <= 0 && numTypeFromStartDate >= MAX_DATE))
            throw new ValidatedException(ERROR_MSG, HttpStatus.BAD_REQUEST);
    }
}

