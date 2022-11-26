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


    @DisplayName("시간(4) 및 날짜(8) 양식 길이 불만족시 예외")
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
                        ,"2022657012"
                        ,20023197l)
        );
    }

    @DisplayName("시간 및 날짜 지원 범위 불만족시 예외")
    @MethodSource("failCase2Arguments")
    @ParameterizedTest
    public void fail2PersonalScheduleTableCreation(String title
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

    private static Stream<Arguments> failCase2Arguments() {
        return Stream.of(Arguments.arguments("제목"
                        ,"내용"
                        ,"2500"
                        ,"1100"
                        ,"20220131"
                        ,20023197l)
                ,Arguments.arguments("제목"
                        ,"내용"
                        ,"2300"
                        ,"1100"
                        ,"20510131"
                        ,20023197l)
        );
    }

}