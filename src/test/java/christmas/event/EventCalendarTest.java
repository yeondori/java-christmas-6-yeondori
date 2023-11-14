package christmas.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventCalendarTest {

    @DisplayName("1~25일은 크리스마스 할인 기간이다.")
    @Test
    public void isChristmasPeriodTrueCase() {
        int date = 25;

        EventCalendar eventCalendar = new EventCalendar();

        assertThat(eventCalendar.isChristmasPeriod(date)).isTrue();
    }
}