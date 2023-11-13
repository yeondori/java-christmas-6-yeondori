package christmas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.Category.DESSERT;
import static christmas.Category.MAIN;
import static christmas.Discount.*;

public class EventController {
    private final DiscountPolicy discountPolicy;
    private final OrderController orderController;
    private final List<Order> orders;

    private EventController(DiscountPolicy discountPolicy, OrderController orderController, List<Order> orders) {
        this.discountPolicy = discountPolicy;
        this.orderController = orderController;
        this.orders = orders;
    }

    public EventController from(List<Order> receiveOrders) {
        return new EventController(discountPolicy, orderController, receiveOrders);
    }

    public Map<Discount, Integer> getTotalBenefits(int date) {
        Map<Category, Integer> quantityByCategory = OrderController.getQuantityByCategory(orders);

        Map<Discount, Integer> totalBenefits = new HashMap<>();
        totalBenefits.put(CHRISTMAS, discountPolicy.getChristmasDiscount(date));
        totalBenefits.put(SPECIAL, discountPolicy.getSpecialDayDiscount(date));
        totalBenefits.put(WEEKDAY, discountPolicy.getWeekdayDiscount(date, quantityByCategory.get(DESSERT)));
        totalBenefits.put(WEEKEND, discountPolicy.getWeekendDiscount(date, quantityByCategory.get(MAIN)));
        totalBenefits.put(GIFT, discountPolicy.getGiftDiscount(OrderController.getTotalPrice(orders)));

        return totalBenefits;
    }

    public int getTotalBenefitPrice(int date) {
        return getTotalBenefits(date).values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
