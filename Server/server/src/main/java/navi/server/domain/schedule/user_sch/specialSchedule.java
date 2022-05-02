package navi.server.domain.schedule.user_sch;

public class specialSchedule {
    private String name;
    private CertificationScheduleType certificationScheduleType;
}

enum CertificationScheduleType {

    APPLY, //지원일자
    RESULT //결과발표일자
}
