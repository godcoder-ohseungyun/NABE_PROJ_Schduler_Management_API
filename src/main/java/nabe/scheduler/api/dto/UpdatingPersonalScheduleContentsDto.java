package nabe.scheduler.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatingPersonalScheduleContentsDto {
    private Long personalScheduleId;
    private String title;
    private String body;
}
