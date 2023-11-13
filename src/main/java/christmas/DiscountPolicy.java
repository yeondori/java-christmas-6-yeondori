package christmas;

import java.util.Arrays;
import java.util.List;

public class DiscountPolicy {
    private final List<Integer> weekend = Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    private final List<Integer> specialDay = Arrays.asList(3, 10, 17, 24, 25, 31);

    public int getChristmasDiscount(int date) {
        if (date >= 1 && date <= 25) {
            return 1000 + (date - 1) * 100;
        }
        return 0;
    }

    public int getSpecialDayDiscount(int date) {
        if (specialDay.contains(date)) {
            return 1000;
        }
        return 0;
    }

    public int getWeekdayDiscount(int date, int dessertQuantity) {
        if (!weekend.contains(date)) {
            return dessertQuantity * 2023;
        }
        return 0;
    }

    public int getWeekendDiscount(int date, int mainQuantity) {
        if (weekend.contains(date)) {
            return mainQuantity * 2023;
        }
        return 0;
    }
}
