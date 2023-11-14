package christmas.event;

import java.util.Arrays;
import java.util.List;

public class EventCalendar {
    public static final List<Integer> WEEKEND_DAY = Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    public static final List<Integer> SPECIAL_DAY = Arrays.asList(3, 10, 17, 24, 25, 31);
    public static final int CHRISTMAS_START = 1;
    public static final int CHRISTMAS_END = 25;

    public boolean isChristmasPeriod(int date) {
        return (date >= CHRISTMAS_START && date <= CHRISTMAS_END);
    }

    public boolean isSpecialDay(int date) {
        return (SPECIAL_DAY.contains(date));
    }

    public boolean isWeekend(int date) {
        return (WEEKEND_DAY.contains(date));
    }

    public boolean isWeekday(int date) {
        return (!WEEKEND_DAY.contains(date));
    }
}