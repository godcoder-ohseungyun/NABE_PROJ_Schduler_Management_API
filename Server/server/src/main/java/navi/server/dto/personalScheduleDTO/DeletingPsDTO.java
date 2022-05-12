package navi.server.dto.personalScheduleDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeletingPsDTO {
    @JsonProperty(value = "target-date")
    private String targetDate;
}
