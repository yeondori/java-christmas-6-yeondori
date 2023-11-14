package christmas.discount;

import static christmas.discount.Discount.*;
import static christmas.event.EventCalendar.*;

public class DiscountPolicy {
    private final int MIN_ORDER_AMOUNT_FOR_DISCOUNT = 10000;

    private boolean isEligibleForDiscount(int orderAmount) {
        return orderAmount >= MIN_ORDER_AMOUNT_FOR_DISCOUNT;
    }

    public int getChristmasDiscount(int orderAmount, int date) {
        if (!isEligibleForDiscount(orderAmount)) {
            return 0;
        }
        if (date >= CHRISTMAS_START && date <= CHRISTMAS_END) {
            return CHRISTMAS.getBaseDiscount() + (date - 1) * CHRISTMAS.getAdditionalDiscount();
        }
        return 0;
    }

    public int getSpecialDayDiscount(int orderAmount, int date) {
        if (!isEligibleForDiscount(orderAmount)) {
            return 0;
        }
        if (SPECIAL_DAY.contains(date)) {
            return SPECIAL.getBaseDiscount();
        }
        return 0;
    }

    public int getWeekdayDiscount(int orderAmount, int date, int dessertQuantity) {
        if (!isEligibleForDiscount(orderAmount)) {
            return 0;
        }
        if (!WEEKEND_DAY.contains(date)) {
            return dessertQuantity * WEEKDAY.getBaseDiscount();
        }
        return 0;
    }

    public int getWeekendDiscount(int orderAmount, int date, int mainQuantity) {
        if (!isEligibleForDiscount(orderAmount)) {
            return 0;
        }
        if (WEEKEND_DAY.contains(date)) {
            return mainQuantity * WEEKEND.getBaseDiscount();
        }
        return 0;
    }

    public int getGiftDiscount(int orderAmount, int totalPrice) {
        if (!isEligibleForDiscount(orderAmount)) {
            return 0;
        }
        if (totalPrice >= GIFT.getPriceCondition()) {
            return GIFT.getBaseDiscount();
        }
        return 0;
    }
}