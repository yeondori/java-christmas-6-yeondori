package christmas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderController {
    public static List<Order> receiveOrders(Map<String, Integer> orderInput) {
        List<Order> orders = new ArrayList<>();
        orderInput.forEach((menuName, quantity) -> {
            orders.add(Order.createOrderOf(menuName, quantity));
        });

        validateOrders(orders);
        return orders;
    }

    private static void validateOrders(List<Order> orders) {
    }
}
