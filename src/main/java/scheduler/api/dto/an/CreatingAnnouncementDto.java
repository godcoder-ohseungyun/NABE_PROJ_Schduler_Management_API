package scheduler.api.dto.an;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scheduler.api.domain.embededType.EndDate;
import scheduler.api.domain.embededType.OriginalUrl;
import scheduler.api.domain.embededType.StartDate;

import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatingAnnouncementDto {
    private Long id;
    private String title;
    private String originalUrl;
    private String startDate;
    private String endDate;

}
