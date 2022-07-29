package scheduler.api.dto.an;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadingAsubDto {
    //공고일정_구독하다 테이블의 id
    private Long asubId;

    //맵핑되어있는 공고 일정 객체의 contents
    private String title;
    private String originalUrl;
    private String startDate;
    private String endDate;

}
