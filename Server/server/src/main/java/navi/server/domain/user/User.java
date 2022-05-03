package navi.server.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import navi.server.domain.schedule.UserSchedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String password;
    private String email;
    private String name;

    //특별일정
    private ArrayList<UserGoal> userGoals;
    //스캐쥴일정
    private Map<String,UserSchedule> userSchedules;

    public User(String password, String email, String name) {
        this.id = id = 0l;
        this.password = password;
        this.email = email;
        this.name = name;

        this.userGoals = new ArrayList<>();
        this.userSchedules = new HashMap<>();
    }
}
