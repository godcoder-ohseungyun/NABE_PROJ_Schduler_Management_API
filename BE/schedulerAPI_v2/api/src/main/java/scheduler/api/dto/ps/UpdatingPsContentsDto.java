package scheduler.api.dto.ps;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatingPsContentsDto {
    private Long psId;
    private String title;
    private String body;
}
