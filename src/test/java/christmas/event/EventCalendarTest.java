package christmas.event;

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

    @DisplayName("3, 10, 17, 24, 25, 31일은 툭별 할인 기간이다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    public void isSpecialDayTrueCase(int date) {
        EventCalendar eventCalendar = new EventCalendar();

        assertThat(eventCalendar.isSpecialDay(date)).isTrue();
    }


}