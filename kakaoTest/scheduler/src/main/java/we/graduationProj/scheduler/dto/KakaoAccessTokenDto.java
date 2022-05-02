package we.graduationProj.scheduler.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoAccessTokenDto {
    private String token_type; //토큰 타입 bearer 고정
    private String access_token; //토큰 값
    private String id_token;
    private String expires_in; //aceess token 만료시간
    private String refresh_token;
    private String refresh_token_expires_in; //refresh token 만료 시간
    private String scope;
}
