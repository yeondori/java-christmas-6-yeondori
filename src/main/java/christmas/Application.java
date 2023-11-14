package christmas;

import christmas.controller.EventController;
import christmas.order.Order;
import christmas.controller.OrderController;
import christmas.ui.InputView;
import christmas.ui.OutputView;

import java.util.List;
import java.util.Map;

public class Application {
    static InputView inputView = new InputView();

    public static void main(String[] args) {
        int date = requestDate();
        Map<String, Integer> orderInput = requestOrders();

        OrderController orderController = OrderController.receiveOrders(orderInput);
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

    public static Map<String, Integer> requestOrders() {
        while (true) {
            try {
                return inputView.readOrders();
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
