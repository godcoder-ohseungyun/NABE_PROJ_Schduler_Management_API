package scheduler.api.domain.validation;

import scheduler.api.exception.exceptionDomain.ValidatedException;

public interface Validator {
    void validate(String field) throws ValidatedException;
}
