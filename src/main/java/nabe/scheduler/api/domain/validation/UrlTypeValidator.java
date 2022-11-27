package nabe.scheduler.api.domain.validation;

import nabe.scheduler.api.exception.definedException.ValidatedException;
import org.springframework.http.HttpStatus;

import java.util.regex.Pattern;

public class UrlTypeValidator implements Validator{

    private final static String URL_FORMAT_ERROR_MSG = "잘못된 양식의 URL 입니다.";
    private final static String regx = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
    @Override
    public void validate(String field) {

        if(Pattern.matches(regx,field)) return;

        throw new ValidatedException(URL_FORMAT_ERROR_MSG, HttpStatus.BAD_REQUEST);
    }

}
