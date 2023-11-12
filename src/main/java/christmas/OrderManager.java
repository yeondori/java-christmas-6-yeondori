package christmas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static christmas.Category.DRINK;

public class OrderManager {
    private final HashMap<Menu, Integer> orders;
    private final MenuBoard menuBoard;

    private OrderManager(HashMap<Menu, Integer> orders, MenuBoard menuBoard) {
        this.orders = orders;
        this.menuBoard = menuBoard;
    }

    public static OrderManager from(MenuBoard menuBoard) {
        return new OrderManager(new HashMap<>(), menuBoard);
    }

    public void receiveOrders(HashMap<String, Integer> orderInput) {
        validateMenu(orderInput.keySet());
        validateQuantity(orderInput.values());
        validateOrder(orderInput.keySet());

        orderInput.forEach((menuName, quantity) -> {
            Menu orderMenu = menuBoard.findMenu(menuName);
            orders.put(orderMenu, quantity);
        });
    }

    private void validateMenu(Set<String> orderMenus) {
        for (String menu : orderMenus) {
            if (!menuBoard.hasMenu(menu)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private void validateQuantity(Collection<Integer> orderQuantity) {
        int totalQuantity = orderQuantity.stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (totalQuantity < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        if (totalQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 최대 20개까지 주문 가능합니다. 다시 입력해 주세요.");
        }
    }

    public void validateOrder(Set<String> orderMenus) {
        List<Category> categories = orderMenus.stream()
                .map(menu -> menuBoard.findCategory(menu))
                .distinct()
                .toList();

        if (categories.contains(DRINK) && categories.size() == 1) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문하는 것은 불가능합니다. 다시 입력해 주세요.");
        }
    }
}