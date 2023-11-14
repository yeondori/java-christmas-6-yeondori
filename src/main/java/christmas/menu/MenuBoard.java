package christmas.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuBoard {
    private final List<Menu> menuBoard;

    public MenuBoard(List<Menu> menuBoard) {
        this.menuBoard = menuBoard;
    }

    public boolean hasMenu(String name) {
        return menuBoard.stream()
                .anyMatch(menu -> menu.isName(name));
    }

    private Menu findMenu(String name) {
        return menuBoard.stream()
                .filter(menu -> menu.isName(name))
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