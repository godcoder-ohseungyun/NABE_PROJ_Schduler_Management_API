package scheduler.api.dto.ps;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatingPersonalScheduleContentsDto {

    private Long personalScheduleId;
    private String title;
    private String body;
}
