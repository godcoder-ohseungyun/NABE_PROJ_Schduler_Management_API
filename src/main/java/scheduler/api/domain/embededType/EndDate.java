package scheduler.api.domain.embededType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scheduler.api.domain.validation.DateTypeValidator;
import scheduler.api.domain.validation.Validator;
import scheduler.api.exception.exceptionDomain.ValidatedException;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter @Setter
public class EndDate {

    private String endDate;

    private final static Validator validator = new DateTypeValidator();

    private EndDate(String endDate) {
        this.endDate = endDate;
    }

    public static EndDate from(String endDate) throws ValidatedException {
        validator.validate(endDate);
        return new EndDate(endDate);
    }

}