package scheduler.api.dto;

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
