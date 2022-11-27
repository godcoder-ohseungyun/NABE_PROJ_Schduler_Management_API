package nabe.scheduler.api.domain.embededType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nabe.scheduler.api.domain.validation.DateTypeValidator;
import nabe.scheduler.api.domain.validation.Validator;

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

    public static EndDate from(String endDate){
        validator.validate(endDate);
        return new EndDate(endDate);
    }

}
