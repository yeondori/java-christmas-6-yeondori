package christmas;

enum Category {
    APPETIZER, MAIN, DESSERT, DRINK;
}

public class Menu {
    private final String name;
    private final int price;
    private final Category category;

    private Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static Menu of(String name, int price, Category category) {
        return new Menu(name, price, category);
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