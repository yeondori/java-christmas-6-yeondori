package christmas;

import java.util.HashMap;

public class OrderManager {
    private final HashMap<Menu, Integer> orders;
    private final MenuBoard menuBoard;

    public OrderManager(HashMap<Menu, Integer> orders, MenuBoard menuBoard) {
        this.orders = orders;
        this.menuBoard = menuBoard;
    }

    public static OrderManager from(MenuBoard menuBoard) {
        return new OrderManager(new HashMap<>(),menuBoard);
    }

    public void receiveOrders(HashMap<String, Integer> orderInput) {
    }

    public void validateMenu(String name) {
        if(!menuBoard.hasMenu(name)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
