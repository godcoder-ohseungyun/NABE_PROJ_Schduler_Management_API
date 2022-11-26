package nabe.scheduler.api.domain.validation;

import nabe.scheduler.api.exception.definedException.ValidatedException;
import org.springframework.http.HttpStatus;

public class DateTypeValidator implements Validator{

    private final static int MAX_DATE = 20501231;
    private final static int MIN_DATE = 0;
    private final static int DATE_LENGTH = 8;

    private final static String DATE_FORMAT_ERROR_MSG = "잘못된 양식의 날짜 입니다.";
    private final static String DATE_RANGE_OUT_ERROR_MSG = "지원하지 않는 날짜 입니다.";

    @Override
    public void validate(String field) {
        int numTypeFromStartDate = Integer.parseInt(field);

        if(numTypeFromStartDate < MIN_DATE || numTypeFromStartDate > MAX_DATE)
            throw new ValidatedException(DATE_RANGE_OUT_ERROR_MSG, HttpStatus.BAD_REQUEST);

        if (field.length() == DATE_LENGTH) return;

        throw new ValidatedException(DATE_FORMAT_ERROR_MSG, HttpStatus.BAD_REQUEST);
    }
}

