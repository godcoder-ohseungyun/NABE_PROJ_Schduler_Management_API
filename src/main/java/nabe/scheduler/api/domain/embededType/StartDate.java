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
public class StartDate {

    private String startDate;

    //고유 검증 전략 DI
    private final static Validator validator = new DateTypeValidator();

    private StartDate(String startDate) {
        this.startDate = startDate;
    }

    public static StartDate from(String startDate){
        validator.validate(startDate);
        return new StartDate(startDate);
    }


}