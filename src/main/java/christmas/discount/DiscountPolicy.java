package christmas.discount;

import static christmas.discount.Discount.*;
import static christmas.event.EventCalendar.*;

public class DiscountPolicy {

    public int getChristmasDiscount(int date) {
        if (date >= CHRISTMAS_START && date <= CHRISTMAS_END) {
            return CHRISTMAS.getBaseDiscount() + (date - 1) * CHRISTMAS.getAdditionalDiscount();
        }
        return 0;
    }

    public int getSpecialDayDiscount(int date) {
        if (SPECIAL_DAY.contains(date)) {
            return SPECIAL.getBaseDiscount();
        }
        return 0;
    }

    public int getWeekdayDiscount(int date, int dessertQuantity) {
        if (!WEEKEND_DAY.contains(date)) {
            return dessertQuantity * WEEKDAY.getBaseDiscount();
        }
        return 0;
    }

    public int getWeekendDiscount(int date, int mainQuantity) {
        if (WEEKEND_DAY.contains(date)) {
            return mainQuantity * WEEKEND.getBaseDiscount();
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