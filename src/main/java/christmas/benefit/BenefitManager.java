package christmas.benefit;

import christmas.menu.Category;
import christmas.order.Orders;

import java.util.HashMap;
import java.util.Map;

import static christmas.benefit.Discount.*;
import static christmas.menu.Category.DESSERT;
import static christmas.menu.Category.MAIN;

public class BenefitManager {
    private final DiscountPolicy discountPolicy;
    private final int date;

    private BenefitManager(DiscountPolicy discountPolicy, int date) {
        this.discountPolicy = discountPolicy;
        this.date = date;
    }

    public static BenefitManager from(int date) {
        return new BenefitManager(new DiscountPolicy(), date);
    }

    public Map<Discount, Integer> getTotalBenefits(Orders orders) {
        Map<Category, Integer> quantityByCategory = orders.getQuantityByCategory();
        int totalPrice = orders.getTotalPrice();

        Map<Discount, Integer> totalBenefits = new HashMap<>();
        totalBenefits.put(CHRISTMAS, discountPolicy.getChristmasDiscount(totalPrice, date));
        totalBenefits.put(SPECIAL, discountPolicy.getSpecialDayDiscount(totalPrice, date));
        totalBenefits.put(WEEKDAY, discountPolicy.getWeekdayDiscount(totalPrice, date, quantityByCategory.get(DESSERT)));
        totalBenefits.put(WEEKEND, discountPolicy.getWeekendDiscount(totalPrice, date, quantityByCategory.get(MAIN)));
        totalBenefits.put(GIFT, discountPolicy.getGiftDiscount(totalPrice));

        return totalBenefits;
    }

    public static EventBadge getEventBadge(int totalBenefitPrice) {
        if (totalBenefitPrice < EventBadge.STAR.getBenefitPrice()) {
            return EventBadge.NONE;
        }
        if (totalBenefitPrice < EventBadge.TREE.getBenefitPrice()) {
            return EventBadge.STAR;
        }
        if (totalBenefitPrice < EventBadge.SANTA.getBenefitPrice()) {
            return EventBadge.TREE;
        }
        return EventBadge.SANTA;
    }

    public int getTotalBenefitPrice(Orders orders) {
        return getTotalBenefits(orders).values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getTotalPayment(Orders orders) {
        int totalPayment = orders.getTotalPrice() - getTotalBenefitPrice(orders);
        int giftDiscount = getTotalBenefits(orders).get(GIFT);
        return totalPayment + giftDiscount;
    }

    public int getDate() {
        return date;
    }
}