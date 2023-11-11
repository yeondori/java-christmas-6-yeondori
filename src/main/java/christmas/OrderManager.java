package christmas;

import java.util.HashMap;

public class OrderManager {
    private final HashMap<Menu, Integer> orders = new HashMap<>();

    private OrderManager(MenuBoard menuBoard) {
    }

    public static OrderManager from(MenuBoard menuBoard) {
        return new OrderManager(menuBoard);
    }
}
