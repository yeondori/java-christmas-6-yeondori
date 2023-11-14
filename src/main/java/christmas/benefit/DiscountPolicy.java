package christmas.benefit;

import static christmas.benefit.Discount.*;

public class DiscountPolicy {
    private final EventCalendar eventCalendar = new EventCalendar();

    public int getChristmasDiscount(int orderAmount, int date) {
        if (orderAmount < CHRISTMAS.getPriceCondition()) {
            return 0;
        }
        if (eventCalendar.isChristmasPeriod(date)) {
            return CHRISTMAS.getBaseDiscount() + (date - 1) * CHRISTMAS.getAdditionalDiscount();
        }
        return 0;
    }

    public int getSpecialDayDiscount(int orderAmount, int date) {
        if (orderAmount < SPECIAL.getPriceCondition()) {
            return 0;
        }
        if (eventCalendar.isSpecialDay(date)) {
            return SPECIAL.getBaseDiscount();
        }
        return 0;
    }

    public int getWeekdayDiscount(int orderAmount, int date, int dessertQuantity) {
        if (orderAmount < WEEKDAY.getPriceCondition()) {
            return 0;
        }
        if (eventCalendar.isWeekday(date)) {
            return dessertQuantity * WEEKDAY.getBaseDiscount();
        }
        return 0;
    }

    public int getWeekendDiscount(int orderAmount, int date, int mainQuantity) {
        if (orderAmount < WEEKEND.getPriceCondition()) {
            return 0;
        }
        if (eventCalendar.isWeekend(date)) {
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