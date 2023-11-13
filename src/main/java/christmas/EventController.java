package christmas;

import java.util.List;

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
}
