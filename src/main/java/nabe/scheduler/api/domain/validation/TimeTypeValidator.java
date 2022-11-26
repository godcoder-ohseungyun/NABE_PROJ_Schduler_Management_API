package nabe.scheduler.api.domain.validation;

import nabe.scheduler.api.exception.definedException.ValidatedException;
import org.springframework.http.HttpStatus;

public class TimeTypeValidator implements Validator{
    private final static int MAX_TIME = 2400;
    private final static int MIN_TIME = 0;
    private final static int TIME_FORMAT_LENGTH = 4;

    private final static String TIME_FORMAT_ERROR_MSG = "잘못된 양식의 시간 입니다.";
    private final static String TIME_RANGE_OUT_ERROR_MSG = "잘못된 양식의 시간 입니다.";
    @Override
    public void validate(String field){
        int numTypeFromStartDate = Integer.parseInt(field);

        if(numTypeFromStartDate < MIN_TIME || numTypeFromStartDate > MAX_TIME)
            throw new ValidatedException(TIME_RANGE_OUT_ERROR_MSG, HttpStatus.BAD_REQUEST);

        if (field.length() == TIME_FORMAT_LENGTH ) return;

        throw new ValidatedException(TIME_FORMAT_ERROR_MSG, HttpStatus.BAD_REQUEST);
    }
}
