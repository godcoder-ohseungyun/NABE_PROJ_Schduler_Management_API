package navi.server.domain.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGoal {
    private Long id;
    private String name;
    private String deadline = "2023-06-02";
}
