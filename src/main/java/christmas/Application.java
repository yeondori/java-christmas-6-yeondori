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

        List<Order> orders = OrderController.receiveOrders(orderInput);
        EventController eventController = EventController.from(orders, date);

        getResults(orders, eventController);
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

    private static void getResults(List<Order> orders, EventController eventController) {
        OutputView outputView = OutputView.of(orders, eventController);
        outputView.printResult();
    }
}
