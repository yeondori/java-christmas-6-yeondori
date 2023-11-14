package christmas.ui;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashMap;
import java.util.Map;

public class InputView {

    private final String INVALID_DATE_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private final String INVALID_ORDER_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public int ReadDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();

        return parseDate(input);
    }

    private int parseDate(String input) {
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }

        int date = Integer.parseInt(input);

        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
        return date;
    }

    public Map<String, Integer> ReadOrders() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();

        return validateOrders(input);
    }

    private Map<String, Integer> validateOrders(String input) {
        if (!input.matches("[가-힣\\d,-]+")) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }

        String[] inputs = input.split(",");
        parseMenuAndQuantity(inputs);

    }

    private Map<String, Integer> parseMenuAndQuantity(String[] inputs) {
        Map<String, Integer> orderInput = new HashMap<>();

        for (String input : inputs) {
            validaeOrderFormat(input);

            String[] menuAndQuantity = input.split("-");
            validateMenu(menuAndQuantity[0]);
            validateNumber(menuAndQuantity[1]);

            orderInput.put(menuAndQuantity[0], Integer.parseInt(menuAndQuantity[1]));
        }

        if (inputs.length != orderInput.size()) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }

        return orderInput;
    }

    private void validaeOrderFormat(String input) {
        if (!input.contains("-")) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private void validateMenu(String input) {
        if (!input.matches("[가-힣]+")) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private void validateNumber(String input) {
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }
}
