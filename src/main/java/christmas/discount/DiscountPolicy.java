package christmas.discount;

import static christmas.discount.Discount.*;
import static christmas.event.EventCalendar.*;

public class DiscountPolicy {
    private final int MIN_ORDER_AMOUNT_FOR_DISCOUNT = 10000;

    public int getChristmasDiscount(int orderAmount, int date) {
        if (orderAmount < CHRISTMAS.getPriceCondition()) {
            return 0;
        }
        if (date >= CHRISTMAS_START && date <= CHRISTMAS_END) {
            return CHRISTMAS.getBaseDiscount() + (date - 1) * CHRISTMAS.getAdditionalDiscount();
        }
        return 0;
    }

    public int getSpecialDayDiscount(int orderAmount, int date) {
        if (orderAmount < SPECIAL.getPriceCondition()) {
            return 0;
        }
        if (SPECIAL_DAY.contains(date)) {
            return SPECIAL.getBaseDiscount();
        }
        return 0;
    }

    public int getWeekdayDiscount(int orderAmount, int date, int dessertQuantity) {
        if (orderAmount < WEEKDAY.getPriceCondition()) {
            return 0;
        }
        if (!WEEKEND_DAY.contains(date)) {
            return dessertQuantity * WEEKDAY.getBaseDiscount();
        }
        return 0;
    }

    public int getWeekendDiscount(int orderAmount, int date, int mainQuantity) {
        if (orderAmount < WEEKEND.getPriceCondition()) {
            return 0;
        }
        if (WEEKEND_DAY.contains(date)) {
            return mainQuantity * WEEKEND.getBaseDiscount();
        }
        return 0;
    }

    public int getGiftDiscount(int orderAmount) {
        if (orderAmount >= GIFT.getPriceCondition()) {
            return GIFT.getBaseDiscount();
        }
        return 0;
    }
}