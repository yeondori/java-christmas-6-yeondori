package christmas.controller;

import christmas.event.EventBadge;
import christmas.menu.Category;
import christmas.order.Order;
import christmas.discount.Discount;
import christmas.discount.DiscountPolicy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.menu.Category.DESSERT;
import static christmas.menu.Category.MAIN;
import static christmas.discount.Discount.*;
import static christmas.event.EventBadge.*;

public class EventController {
    private final DiscountPolicy discountPolicy;
    private final OrderController orderController;
    private final int date;

    public EventController(DiscountPolicy discountPolicy, OrderController orderController, int date) {
        this.discountPolicy = discountPolicy;
        this.orderController = orderController;
        this.date = date;
    }

    public static EventController from(OrderController orderController, int date) {
        return new EventController(new DiscountPolicy(), orderController, date);
    }

    public Map<Discount, Integer> getTotalBenefits() {
        Map<Category, Integer> quantityByCategory = orderController.getQuantityByCategory();

        Map<Discount, Integer> totalBenefits = new HashMap<>();
        totalBenefits.put(CHRISTMAS, discountPolicy.getChristmasDiscount(date));
        totalBenefits.put(SPECIAL, discountPolicy.getSpecialDayDiscount(date));
        totalBenefits.put(WEEKDAY, discountPolicy.getWeekdayDiscount(date, quantityByCategory.get(DESSERT)));
        totalBenefits.put(WEEKEND, discountPolicy.getWeekendDiscount(date, quantityByCategory.get(MAIN)));
        totalBenefits.put(GIFT, discountPolicy.getGiftDiscount(orderController.getTotalPrice()));

        return totalBenefits;
    }

    public static EventBadge getEventBadge(int totalBenefitPrice) {
        if (totalBenefitPrice < 별.getBenefitPrice()) {
            return 미대상;
        }
        if (totalBenefitPrice < 트리.getBenefitPrice()) {
            return 별;
        }
        if (totalBenefitPrice < 산타.getBenefitPrice()) {
            return 트리;
        }
        return 산타;
    }

    public int getOrderPrice() {
        return orderController.getTotalPrice();
    }

    public int getTotalBenefitPrice() {
        return getTotalBenefits().values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getTotalPayment() {
        int totalPrice = getOrderPrice();
        int benefitPrice = getTotalBenefitPrice();

        return totalPrice - benefitPrice;
    }
}