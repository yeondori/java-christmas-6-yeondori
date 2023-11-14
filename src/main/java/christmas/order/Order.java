package christmas.order;

import christmas.menu.Category;
import christmas.menu.Menu;
import christmas.menu.MenuBoard;

public class Order {
    private final MenuBoard menuBoard;
    private final Category category;
    private final String menuName;
    private final int quantity;

    private Order(MenuBoard menuBoard, Category category, String menuName, int quantity) {
        this.menuBoard = menuBoard;
        this.category = category;
        this.menuName = menuName;
        this.quantity = quantity;
    }

    public static Order createOrderOf(MenuBoard menuBoard, String menuName, int quantity) {
        validateMenu(menuBoard, menuName);
        validateQuantity(quantity);

        Category category = menuBoard.findCategory(menuName);

        return new Order(menuBoard, category, menuName, quantity);
    }

    private static void validateMenu(MenuBoard menuBoard, String menuName) {
        if (!menuBoard.hasMenu(menuName)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        if (quantity > 20) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 최대 20개까지 주문 가능합니다. 다시 입력해 주세요.");
        }
    }

    public int calculateOrderPrice() {
        int price = menuBoard.findPrice(this.menuName);
        int quantity = this.quantity;

        return price * quantity;
    }

    public boolean isCategory(Category category) {
        return this.category.equals(category);
    }

    public Category getCategory() {
        return category;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getQuantity() {
        return quantity;
    }
}
