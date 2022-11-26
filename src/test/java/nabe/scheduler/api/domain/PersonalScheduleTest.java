package nabe.scheduler.api.domain;


import nabe.scheduler.api.exception.definedException.ValidatedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

class PersonalScheduleTest {

    @MethodSource("successCaseArguments")
    @ParameterizedTest
    public void createPersonalScheduleTable(String title
            ,String body
            ,String startTime
            ,String endTime
            ,String startDate
            ,Long memberId){

        assertThat(PersonalSchedule.createPersonalSchedule(title
                ,body
                ,startTime
                ,endTime
                ,startDate
                ,memberId)).isInstanceOf(PersonalSchedule.class);
    }

    private static Stream<Arguments> successCaseArguments() {
        return Stream.of(Arguments.arguments("제목"
                ,"내용"
                ,"1020"
                ,"1100"
                ,"20221020"
                ,20023197l)
        );
    }


    @DisplayName("시간 및 날짜 양식 불만족시 예외")
    @MethodSource("failCaseArguments")
    @ParameterizedTest
    public void failPersonalScheduleTableCreation(String title
            ,String body
            ,String startTime
            ,String endTime
            ,String startDate
            ,Long memberId){

        assertThatThrownBy(()->assertThat(PersonalSchedule.createPersonalSchedule(title
                ,body
                ,startTime
                ,endTime
                ,startDate
                ,memberId)))
                .isInstanceOf(ValidatedException.class);
    }

    private static Stream<Arguments> failCaseArguments() {
        return Stream.of(Arguments.arguments("제목"
                ,"내용"
                ,"102087"
                ,"1100"
                ,"20221020",20023197l)
                ,Arguments.arguments("제목"
                        ,"내용"
                        ,"1020"
                        ,"1100"
                        ,"2022901020"
                        ,20023197l)
        );
    }

}