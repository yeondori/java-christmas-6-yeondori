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
        totalBenefits.put(GIFT, discountPolicy.getGiftDiscount(totalPrice));

        return totalBenefits;
    }

    public static EventBadge getEventBadge(int totalBenefitPrice) {
        if (totalBenefitPrice < STAR.getBenefitPrice()) {
            return NONE;
        }
        if (totalBenefitPrice < TREE.getBenefitPrice()) {
            return STAR;
        }
        if (totalBenefitPrice < SANTA.getBenefitPrice()) {
            return TREE;
        }
        return SANTA;
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
        int totalPayment = getOrderPrice() - getTotalBenefitPrice();
        int giftDiscount = getTotalBenefits().get(GIFT).intValue();
        return totalPayment + giftDiscount;
    }

    public int getDate() {
        return date;
    }
}