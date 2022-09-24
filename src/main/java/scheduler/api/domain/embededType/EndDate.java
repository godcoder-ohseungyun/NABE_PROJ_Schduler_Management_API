package scheduler.api.domain.embededType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scheduler.api.domain.validation.DateTypeValidator;
import scheduler.api.domain.validation.Validator;
import scheduler.api.exception.userDefinedException.ValidatedException;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter @Setter
public class EndDate {

    private String endDate;

    //고유 검증 전략 DI
    private final static Validator validator = new DateTypeValidator();

    private EndDate(String endDate) {
        this.endDate = endDate;
    }

    public static EndDate from(String endDate) throws ValidatedException {
        validator.validate(endDate);
        return new EndDate(endDate);
    }

}
