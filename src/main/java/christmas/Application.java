package christmas;

import christmas.controller.EventController;
import christmas.controller.OrderController;
import christmas.menu.Category;
import christmas.menu.Menu;
import christmas.menu.MenuBoard;
import christmas.ui.InputView;
import christmas.ui.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Application {
    static InputView inputView = new InputView();

    public static void main(String[] args) {
        MenuBoard menuBoard = initMenuBoard();
        int date = requestDate();

        OrderController orderController = requestOrders(menuBoard);
        EventController eventController = EventController.from(orderController, date);

        getResults(orderController, eventController);
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

    public static int requestDate() {
        while (true) {
            try {
                return inputView.readDate();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static OrderController requestOrders(MenuBoard menuBoard) {
        while (true) {
            try {
                Map<String, Integer> orders = inputView.readOrders();
                return OrderController.receiveOrders(menuBoard, orders);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void getResults(OrderController orderController, EventController eventController) {
        OutputView outputView = new OutputView(orderController, eventController);
        outputView.printResult();
    }
}