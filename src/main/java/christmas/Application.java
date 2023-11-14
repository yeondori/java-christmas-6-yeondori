package christmas;

import christmas.ui.InputView;

import java.util.Map;

public class Application {
    static InputView inputView = new InputView();

    public static void main(String[] args) {
        int date = requestDate();

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
