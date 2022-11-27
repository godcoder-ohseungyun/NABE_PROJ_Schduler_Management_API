package nabe.scheduler.api.domain;

import nabe.scheduler.api.exception.definedException.ValidatedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RecruitmentScheduleTest {

    @MethodSource("successCaseArguments")
    @ParameterizedTest
    public void createRecruitmentScheduleTable(
            Long id
            , String title
            , String originalUrl
            , String startDate
            , String endDate
    ) {

        assertThat(RecruitmentSchedule.createRecruitmentSchedule(
                id
                , title
                , originalUrl
                , startDate
                , endDate
        )).isInstanceOf(RecruitmentSchedule.class);
    }

    private static Stream<Arguments> successCaseArguments() {
        return Stream.of(Arguments.arguments(
                        1l
                        , "제목"
                        , "https://www.test.com"
                        , "20221020"
                        , "20221024"
                )
        );
    }


    @DisplayName("url 양식 및 날짜 양식 불만족시 예외")
    @MethodSource("failCaseArguments")
    @ParameterizedTest
    public void failRecruitmentScheduleTableCreation(
            Long id
            , String title
            , String originalUrl
            , String startDate
            , String endDate
    ) {

        assertThatThrownBy(() -> RecruitmentSchedule.createRecruitmentSchedule(
                id
                , title
                , originalUrl
                , startDate
                , endDate
        )).isInstanceOf(ValidatedException.class);
    }

    private static Stream<Arguments> failCaseArguments() {
        return Stream.of(Arguments.arguments(
                        1l
                        , "제목1"
                        , "httpsdf://wdfww.test.com"
                        , "20221020"
                        , "20221024"
                )
                , Arguments.arguments(
                        2l
                        , "제목2"
                        , "https://www.test.com"
                        , "1990201112"
                        , "20221024"
                )
        );
    }
}