package nabe.scheduler.api.domain.embededType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nabe.scheduler.api.domain.validation.UrlTypeValidator;
import nabe.scheduler.api.domain.validation.Validator;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class OriginalUrl {

    private String originalUrl;

    //고유 검증 전략 DI
    private final static Validator validator = new UrlTypeValidator();

    private OriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public static OriginalUrl from(String originalUrl){
        validator.validate(originalUrl);
        return new OriginalUrl(originalUrl);
    }
}
