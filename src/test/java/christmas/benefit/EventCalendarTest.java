package christmas.benefit;

import christmas.benefit.EventCalendar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class EventCalendarTest {

    @DisplayName("1~25일은 크리스마스 할인 기간이다.")
    @Test
    public void isChristmasPeriodTrueCase() {
        int date = 25;

        EventCalendar eventCalendar = new EventCalendar();

        assertThat(eventCalendar.isChristmasPeriod(date)).isTrue();
    }

    @DisplayName("25일 이후는 크리스마스 할인 기간이 아니다.")
    @Test
    public void isChristmasPeriodFalseCase() {
        int date = 26;

        EventCalendar eventCalendar = new EventCalendar();

        assertThat(eventCalendar.isChristmasPeriod(date)).isFalse();
    }

    @DisplayName("3, 10, 17, 24, 25, 31일은 특별 할인 기간이다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    public void isSpecialDayTrueCase(int date) {
        EventCalendar eventCalendar = new EventCalendar();

        assertThat(eventCalendar.isSpecialDay(date)).isTrue();
    }

    @DisplayName("3, 10, 17, 24, 25, 31일을 제외한 날은 특별 할인 기간이 아니다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 26, 27, 28, 29, 30})
    public void isSpecialDayFalseCase(int date) {
        EventCalendar eventCalendar = new EventCalendar();

        assertThat(eventCalendar.isSpecialDay(date)).isFalse();
    }

    @DisplayName("1, 2, 8, 9, 15, 16, 22, 23, 29, 30일은 주말 할인 기간이다.(평일 할인 기간이 아니다)")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    public void isWeekendTrueAndIsWeekdayFalseCase(int date) {
        EventCalendar eventCalendar = new EventCalendar();

        assertThat(eventCalendar.isWeekend(date)).isTrue();
        assertThat(eventCalendar.isWeekday(date)).isFalse();
    }

    @DisplayName("1, 2, 8, 9, 15, 16, 22, 23, 29, 30일은 주말 할인 기간이다.(평일 할인 기간이 아니다)")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    public void isWeekendFalseAndIsWeekdayTrueCase(int date) {
        EventCalendar eventCalendar = new EventCalendar();

        assertThat(eventCalendar.isWeekend(date)).isFalse();
        assertThat(eventCalendar.isWeekday(date)).isTrue();
    }
}