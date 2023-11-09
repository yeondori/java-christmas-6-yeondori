package christmas;

public class Food {
    private final String name;
    private final int price;
    private final Category category;

    public Food(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}