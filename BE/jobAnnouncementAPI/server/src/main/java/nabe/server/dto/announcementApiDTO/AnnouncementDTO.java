package nabe.server.dto.announcementApiDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {
    private int count;
    private int total;
    private List<nabe.server.dto.announcementApiDTO.Job> job;
}


