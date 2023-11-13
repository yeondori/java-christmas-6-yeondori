package christmas;

public class Order {
    private final Category category;
    private final String menuName;
    private final int quantity;

    public Order(Category category, String menuName, int quantity) {
        this.category = category;
        this.menuName = menuName;
        this.quantity = quantity;
    }

}
