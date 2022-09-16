package scheduler.api.domain.validation;

import scheduler.api.exception.userDefinedException.ValidatedException;

public interface Validator {
    void validate(String field) throws ValidatedException;
}
