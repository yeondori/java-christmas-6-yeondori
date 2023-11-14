package christmas.menu;

public class Menu {
    private final String name;
    private final int price;
    private final Category category;

    public Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public boolean isName(String name) {
        return (this.name.equals(name));
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}