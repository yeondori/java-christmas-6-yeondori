package christmas;

import christmas.benefit.BenefitManager;
import christmas.menu.Category;
import christmas.menu.Menu;
import christmas.menu.MenuBoard;
import christmas.order.Orders;
import christmas.ui.InputView;
import christmas.ui.OutputView;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        int date = inputView.requestDate();

        MenuBoard menuBoard = initMenuBoard();

        Orders orders = inputView.requestOrders(menuBoard);
        BenefitManager benefitManager = BenefitManager.from(date);

        OutputView outputView = new OutputView(orders, benefitManager);
        outputView.printResult();
    }

    public static MenuBoard initMenuBoard() {
        List<Menu> menus = Arrays.asList(
                new Menu("양송이수프", 6_000, Category.APPETIZER),
                new Menu("타파스", 5_500, Category.APPETIZER),
                new Menu("시저샐러드", 8_000, Category.APPETIZER),
                new Menu("티본스테이크", 55_000, Category.MAIN),
                new Menu("바비큐립", 54_000, Category.MAIN),
                new Menu("해산물파스타", 35_000, Category.MAIN),
                new Menu("크리스마스파스타", 25_000, Category.MAIN),
                new Menu("초코케이크", 15_000, Category.DESSERT),
                new Menu("아이스크림", 5_000, Category.DESSERT),
                new Menu("제로콜라", 3_000, Category.DRINK),
                new Menu("레드와인", 60_000, Category.DRINK),
                new Menu("샴페인", 25_000, Category.DRINK)
        );

        return new MenuBoard(menus);
    }
}