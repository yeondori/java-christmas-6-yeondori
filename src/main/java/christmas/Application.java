package christmas;

import christmas.event.EventController;
import christmas.order.Order;
import christmas.order.OrderController;
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

        OutputView outputView = OutputView.of(orders, eventController);
        outputView.printResult();
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

}
