package scheduler.api.domain.embededType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scheduler.api.domain.validation.DateTypeValidator;
import scheduler.api.domain.validation.TimeTypeValidator;
import scheduler.api.domain.validation.Validator;
import scheduler.api.exception.exceptionDomain.ValidatedException;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class EndTime {

    private String endTime;

    private final static Validator validator = new TimeTypeValidator();

    private EndTime(String endTime) {
        this.endTime = endTime;
    }

    public static EndTime from(String endTime) throws ValidatedException {
        validator.validate(endTime);
        return new EndTime(endTime);
    }


}
