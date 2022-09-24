package scheduler.api.domain.embededType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scheduler.api.domain.validation.TimeTypeValidator;
import scheduler.api.domain.validation.Validator;
import scheduler.api.exception.userDefinedException.ValidatedException;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class StartTime {

    private String startTime;

    //고유 검증 전략 DI
    private final static Validator validator = new TimeTypeValidator();

    private StartTime(String startTime) {
        this.startTime = startTime;
    }

    public static StartTime from(String startTime) throws ValidatedException {
        validator.validate(startTime);
        return new StartTime(startTime);
    }


}
