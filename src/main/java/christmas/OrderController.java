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
        validateTotalQuantity(orders);
        validateDuplicateMenu(orders);
        validateOnlyDrink(orders);
    }

    private static void validateTotalQuantity(List<Order> orders) {
        int totalQuantity = orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();

        if (totalQuantity>20) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 최대 20개까지 주문 가능합니다. 다시 입력해 주세요.");
        }
    }

    private static void validateDuplicateMenu(List<Order> orders) {
        int uniqueMenuSize = orders.stream()
                .map(Order::getMenuName)
                .distinct()
                .toList()
                .size();

        if (uniqueMenuSize != orders.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateOnlyDrink(List<Order> orders) {
    }
}
