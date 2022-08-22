package scheduler.api.domain.validation;

import org.springframework.http.HttpStatus;
import scheduler.api.exception.exceptionDomain.ValidatedException;

import java.util.regex.Pattern;

public class UrlTypeValidator implements Validator{

    private final static String ERROR_MSG = "잘못된 양식의 URL 입니다. (양식: https://www.nabe.com)";
    private final static String regx = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
    @Override
    public void validate(String field) throws ValidatedException {
        if(Pattern.matches(regx,field)==false) throw new ValidatedException(ERROR_MSG, HttpStatus.BAD_REQUEST);
    }

}
