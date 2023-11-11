package christmas;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class OrderManager {
    private final HashMap<Menu, Integer> orders;
    private final MenuBoard menuBoard;

    public OrderManager(HashMap<Menu, Integer> orders, MenuBoard menuBoard) {
        this.orders = orders;
        this.menuBoard = menuBoard;
    }

    public static OrderManager from(MenuBoard menuBoard) {
        return new OrderManager(new HashMap<>(), menuBoard);
    }

    public void receiveOrders(HashMap<String, Integer> orderInput) {
    }

    public void validateMenu(Set<String> orderMenus) {
        for(String menu : orderMenus) {
            if (!menuBoard.hasMenu(menu)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    public void validateQuantity(Collection<Integer> orderQuantity) {
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
}
