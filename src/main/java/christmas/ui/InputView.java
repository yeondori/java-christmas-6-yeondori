package christmas.ui;

import camp.nextstep.edu.missionutils.Console;
import christmas.menu.MenuBoard;
import christmas.order.Orders;

import java.util.HashMap;
import java.util.Map;

public class InputView {

    private static final String INVALID_DATE_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String INVALID_ORDER_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public int requestDate() {
        while (true) {
            try {
                return readDate();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Orders requestOrders(MenuBoard menuBoard) {
        while (true) {
            try {
                Map<String, Integer> orders = readOrders();
                return Orders.receiveOrders(menuBoard, orders);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int readDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

        String input = Console.readLine();

        return parseDate(input);
    }

    private int parseDate(String input) {
        validateFormat(input, "\\d+", INVALID_DATE_MESSAGE);
        int date = Integer.parseInt(input);

        validateDate(date);
        return date;
    }

    private void validateDate(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
    }

    public Map<String, Integer> readOrders() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();

        return parseOrders(input);
    }

    private Map<String, Integer> parseOrders(String input) {
        validateFormat(input, "[가-힣\\d,-]+", INVALID_ORDER_MESSAGE);
        String[] inputs = input.split(",");

        return parseMenuAndQuantity(inputs);
    }

    private Map<String, Integer> parseMenuAndQuantity(String[] inputs) {
        Map<String, Integer> orderInput = new HashMap<>();

        for (String input : inputs) {
            validateOrderFormat(input);

            String[] menuAndQuantity = input.split("-");
            validateFormat(menuAndQuantity[0], "[가-힣]+", INVALID_ORDER_MESSAGE);
            validateFormat(menuAndQuantity[1], "\\d+", INVALID_ORDER_MESSAGE);

            orderInput.put(menuAndQuantity[0], Integer.parseInt(menuAndQuantity[1]));
        }

        validateDuplicate(inputs, orderInput);
        return orderInput;
    }

    private void validateOrderFormat(String input) {
        if (!input.contains("-")) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private void validateDuplicate(String[] inputs, Map<String, Integer> orderInput) {
        if (inputs.length != orderInput.size()) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private void validateFormat(String input, String regex, String errorMessage) {
        if (!input.matches(regex)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}