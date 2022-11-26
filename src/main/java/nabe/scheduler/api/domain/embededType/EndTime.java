package nabe.scheduler.api.domain.embededType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nabe.scheduler.api.domain.validation.TimeTypeValidator;
import nabe.scheduler.api.domain.validation.Validator;


import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class EndTime {

    private String endTime;

    ///고유 검증 전략 DI
    private final static Validator validator = new TimeTypeValidator();

    private EndTime(String endTime) {
        this.endTime = endTime;
    }

    public static EndTime from(String endTime){
        validator.validate(endTime);
        return new EndTime(endTime);
    }


}
