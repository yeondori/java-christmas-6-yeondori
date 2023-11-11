package christmas;

import java.util.ArrayList;
import java.util.List;

import static christmas.Category.*;


public class Menu {
    private final List<Food> menu;

    public Menu() {
        menu = new ArrayList<>();
        initializeMenu();
    }

    private void initializeMenu() {
        addAppetizer();
        addMain();
        addDessert();
        addBeberage();
    }

    private void addAppetizer() {
        menu.add(new Food("양송이수프", 6000, APPETIZER));
        menu.add(new Food("타파스", 5500, APPETIZER));
        menu.add(new Food("시저샐러드", 8000, APPETIZER));
    }

    private void addMain() {
        menu.add(new Food("티본스테이크", 55000, MAIN));
        menu.add(new Food("바비큐립", 54000, MAIN));
        menu.add(new Food("해산물파스타", 35000, MAIN));
        menu.add(new Food("크리스마스파스타", 25000, MAIN));
    }

    private void addDessert() {
        menu.add(new Food("초코케이크", 15000, DESSERT));
        menu.add(new Food("아이스크림", 5000, DESSERT));
    }

    private void addBeberage() {
        menu.add(new Food("제로콜라", 3000, BEVERAGE));
        menu.add(new Food("레드와인", 60000, BEVERAGE));
        menu.add(new Food("샴페인", 25000, BEVERAGE));
    }

    public boolean hasMenu(String name) {
        return menu.stream().
                anyMatch(food -> food.getName().equals(name));
    }

    public Food findMenu(String name) {
        return menu.stream()
                .filter(food -> food.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Category findCategory(String name) {
        return menu.stream()
                .filter(food -> food.getName().equals(name))
                .findFirst()
                .map(Food::getCategory)
                .orElse(null);
    }
}