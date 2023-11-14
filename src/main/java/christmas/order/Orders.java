package christmas.order;

import christmas.menu.Category;
import christmas.menu.MenuBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static christmas.menu.Category.DRINK;

public class Orders {

    private final List<Order> orders;

    private Orders(List<Order> orders) {
        this.orders = orders;
    }

    public static Orders receiveOrders(MenuBoard menuBoard, Map<String, Integer> orderInput) {
        List<Order> receiveOrders = new ArrayList<>();
        orderInput.forEach((menuName, quantity) -> {
            receiveOrders.add(Order.createOrderOf(menuBoard, menuName, quantity));
        });

        validateOrders(receiveOrders);
        return new Orders(receiveOrders);
    }

    private static void validateOrders(List<Order> orders) {
        validateTotalQuantity(orders);
        validateOnlyDrink(orders);
    }

    private static void validateTotalQuantity(List<Order> orders) {
        int totalQuantity = orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();

        if (totalQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 최대 20개까지 주문 가능합니다. 다시 입력해 주세요.");
        }
    }

    private static void validateOnlyDrink(List<Order> orders) {
        List<Category> categories = orders.stream()
                .map(Order::getCategory)
                .distinct()
                .toList();

        if (categories.size() == 1 && categories.contains(DRINK)) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문하는 것은 불가합니다. 다시 입력해 주세요.");
        }
    }

    public int getTotalPrice() {
        return orders.stream()
                .mapToInt(Order::calculateOrderPrice)
                .sum();
    }

    public Map<String, Integer> getOrderHistory() {
        return orders.stream()
                .collect(Collectors.toMap(Order::getMenuName, Order::getQuantity));
    }

    public Map<Category, Integer> getQuantityByCategory() {
        return Arrays.stream(Category.values())
                .collect(Collectors.toMap(category -> category, this::getTotalQuantity));
    }

    private int getTotalQuantity(Category category) {
        return orders.stream()
                .filter(order -> order.isCategory(category))
                .mapToInt(Order::getQuantity)
                .sum();
    }
}