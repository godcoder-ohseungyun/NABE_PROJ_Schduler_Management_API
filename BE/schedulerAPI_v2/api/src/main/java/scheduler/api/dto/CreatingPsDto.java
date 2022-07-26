package scheduler.api.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatingPsDto {
    private String title;
    private String body;
    private String startTime;
    private String endTime; //2330 -> 밤 11시 30분
    private String date; //20220726
}
