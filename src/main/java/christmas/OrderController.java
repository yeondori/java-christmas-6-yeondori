package christmas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static christmas.Category.*;

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
        List<Category> categories = orders.stream()
                .map(Order::getCategory)
                .distinct()
                .toList();

        if(categories.size()==1 && categories.contains(DRINK)) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문하는 것은 불가합니다. 다시 입력해 주세요.");
        }
    }
}
