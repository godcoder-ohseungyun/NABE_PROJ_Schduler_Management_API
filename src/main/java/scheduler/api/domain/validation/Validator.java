package scheduler.api.domain.validation;

import scheduler.api.exception.userDefinedException.ValidatedException;

/**
 * @detail
 * - 임베디드 타입별 고유 검증 전략들의 인터페이스 입니다. (임베디드 타입들은 전략패턴을 통해 필요한 검증 전략을 사용합니다.)
 */
public interface Validator {
    void validate(String field) throws ValidatedException;
}
