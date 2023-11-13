package christmas;

import java.util.Arrays;
import java.util.List;

import static christmas.Discount.*;

public class DiscountPolicy {
    private final List<Integer> weekend = Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    private final List<Integer> specialDay = Arrays.asList(3, 10, 17, 24, 25, 31);

    public int getChristmasDiscount(int date) {
        if (date >= 1 && date <= 25) {
            return CHRISTMAS.getBaseDiscount() + (date - 1) * CHRISTMAS.getAdditionalDiscount();
        }
        return 0;
    }

    public int getSpecialDayDiscount(int date) {
        if (specialDay.contains(date)) {
            return SPECIAL.getBaseDiscount();
        }
        return 0;
    }

    public int getWeekdayDiscount(int date, int dessertQuantity) {
        if (!weekend.contains(date)) {
            return dessertQuantity * WEEKDAY.getAdditionalDiscount();
        }
        return 0;
    }

    public int getWeekendDiscount(int date, int mainQuantity) {
        if (weekend.contains(date)) {
            return mainQuantity * WEEKEND.getAdditionalDiscount();
        }
        return 0;
    }

    public int getGiftDiscount(int totalPrice) {
        if (totalPrice >= GIFT.getPriceCondition()) {
            return GIFT.getBaseDiscount();
        }
        return 0;
    }
}