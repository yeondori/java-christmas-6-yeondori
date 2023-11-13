package christmas;

public class Order {
    private static final MenuBoard menuBoard = MenuBoard.loadMenu();
    private final Category category;
    private final String menuName;
    private final int quantity;


    private Order(Category category, String menuName, int quantity) {
        this.category = category;
        this.menuName = menuName;
        this.quantity = quantity;
    }

    public static Order createOrderOf(String menuName, int quantity) {
        validateMenu(menuName);
        validateQuantity(quantity);

        Category category = menuBoard.findCategory(menuName);

        return new Order(category, menuName, quantity);
    }

    private static void validateMenu(String menuName) {
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

    public static int calculateOrderPrice(Order order) {
        int price = menuBoard.findPrice(order.getMenuName());
        int quantity = order.quantity;

        return price * quantity;
    }

    public int getPrice() {
        return menuBoard.findPrice(menuName);
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
