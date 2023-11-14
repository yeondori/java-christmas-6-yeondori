package christmas.controller;

import christmas.discount.Discount;
import christmas.discount.DiscountPolicy;
import christmas.event.EventBadge;
import christmas.menu.Category;

import java.util.HashMap;
import java.util.Map;

import static christmas.discount.Discount.*;
import static christmas.event.EventBadge.*;
import static christmas.menu.Category.DESSERT;
import static christmas.menu.Category.MAIN;

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
        int totalPrice = orderController.getTotalPrice();

        Map<Discount, Integer> totalBenefits = new HashMap<>();
        totalBenefits.put(CHRISTMAS, discountPolicy.getChristmasDiscount(totalPrice, date));
        totalBenefits.put(SPECIAL, discountPolicy.getSpecialDayDiscount(totalPrice, date));
        totalBenefits.put(WEEKDAY, discountPolicy.getWeekdayDiscount(totalPrice, date, quantityByCategory.get(DESSERT)));
        totalBenefits.put(WEEKEND, discountPolicy.getWeekendDiscount(totalPrice, date, quantityByCategory.get(MAIN)));
        totalBenefits.put(GIFT, discountPolicy.getGiftDiscount(totalPrice, orderController.getTotalPrice()));

        return totalBenefits;
    }

    public static EventBadge getEventBadge(int totalBenefitPrice) {
        if (totalBenefitPrice < 별.getBenefitPrice()) {
            return 없음;
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

    public int getDate() {
        return date;
    }
}