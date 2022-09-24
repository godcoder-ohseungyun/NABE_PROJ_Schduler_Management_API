package scheduler.api.aop.aopDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @detail
 * - '회원 및 인증 관리 서버'에 엑세스 토큰 유효성을 질의 할때 보내는 HTTP BODY
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenValidationDto {
    private String access_token;
}
