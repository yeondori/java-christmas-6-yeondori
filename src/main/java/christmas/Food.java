package christmas;

enum Category {
    APPETIZER, MAIN, DESSERT, BEVERAGE;
}

public class Food {
    private final String name;
    private final int price;
    private final Category category;

    public Food(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}