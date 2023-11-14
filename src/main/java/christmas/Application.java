package christmas;

import christmas.controller.EventController;
import christmas.controller.OrderController;
import christmas.ui.InputView;
import christmas.ui.OutputView;

import java.util.Map;

public class Application {
    static InputView inputView = new InputView();

    public static void main(String[] args) {
        int date = requestDate();

        OrderController orderController = requestOrders();
        EventController eventController = EventController.from(orderController, date);

        getResults(orderController, eventController);
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

    public static OrderController requestOrders() {
        while (true) {
            try {
                Map<String, Integer> orders = inputView.readOrders();
                return OrderController.receiveOrders(orders);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void getResults(OrderController orderController, EventController eventController) {
        OutputView outputView = OutputView.of(orderController, eventController);
        outputView.printResult();
    }
}
