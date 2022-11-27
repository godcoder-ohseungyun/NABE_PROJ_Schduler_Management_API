package nabe.scheduler.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatingRecruitmentScheduleDto {
    private Long id;
    private String title;
    private String originalUrl;
    private String startDate;
    private String endDate;
}
