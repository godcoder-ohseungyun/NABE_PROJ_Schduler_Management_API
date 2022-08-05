package scheduler.api.dto.an;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatingAnnouncementDto {
    @NotNull(message = "사람인에서 제공하는 공고 id는 필수 값입니다.")
    private Long id;
    @NotBlank(message = "제목은 필수 값입니다.")
    private String title;
    @NotBlank(message = "공고 원본 url은 필수 값입니다.")
    private String originalUrl;
    @NotBlank(message = "시작시간은 필수 값입니다.")
    private String startDate;
    @NotBlank(message = "종료시간은 필수 값입니다.")
    private String endDate; //2330 -> 밤 11시 30분
}
