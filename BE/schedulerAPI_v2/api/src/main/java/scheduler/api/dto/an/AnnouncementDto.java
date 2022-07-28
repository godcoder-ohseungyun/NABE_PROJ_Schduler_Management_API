package scheduler.api.dto.an;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDto {
    private Long id;
    private String title;
    private String originalUrl;
    private String startDate;
    private String endDate; //2330 -> 밤 11시 30분
}
