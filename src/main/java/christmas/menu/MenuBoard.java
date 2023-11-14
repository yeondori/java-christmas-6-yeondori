package christmas.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuBoard {
    private final List<Menu> menuBoard = new ArrayList<>();

    private MenuBoard() {
        initMenuBoard();
    }

    public static MenuBoard loadMenu() {
        return new MenuBoard();
    }

    private void initMenuBoard() {
        addAppetizer();
        addMain();
        addDessert();
        addDrink();
    }

    private void addAppetizer() {
        menuBoard.add(Menu.of("양송이수프", 6_000, Category.APPETIZER));
        menuBoard.add(Menu.of("타파스", 5_500, Category.APPETIZER));
        menuBoard.add(Menu.of("시저샐러드", 8_000, Category.APPETIZER));
    }

    private void addMain() {
        menuBoard.add(Menu.of("티본스테이크", 55_000, Category.MAIN));
        menuBoard.add(Menu.of("바비큐립", 54_000, Category.MAIN));
        menuBoard.add(Menu.of("해산물파스타", 35_000, Category.MAIN));
        menuBoard.add(Menu.of("크리스마스파스타", 25_000, Category.MAIN));
    }

    private void addDessert() {
        menuBoard.add(Menu.of("초코케이크", 15_000, Category.DESSERT));
        menuBoard.add(Menu.of("아이스크림", 5_000, Category.DESSERT));
    }

    private void addDrink() {
        menuBoard.add(Menu.of("제로콜라", 3_000, Category.DRINK));
        menuBoard.add(Menu.of("레드와인", 60_000, Category.DRINK));
        menuBoard.add(Menu.of("샴페인", 25_000, Category.DRINK));
    }

    public boolean hasMenu(String name) {
        return menuBoard.stream()
                .anyMatch(menu -> menu.getName().equals(name));
    }

    public Menu findMenu(String name) {
        return menuBoard.stream()
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 메뉴가 존재하지 않습니다."));
    }

    public Category findCategory(String name) {
        return findMenu(name).getCategory();
    }

    public int findPrice(String name) {
        return findMenu(name).getPrice();
    }
}