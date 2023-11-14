package christmas.ui;

import christmas.order.Order;
import christmas.order.OrderController;

import java.util.List;
import java.util.Map;

public class OutputView {
    public void printResult() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public void printMenu(List<Order> orders) {
        System.out.println("<주문 메뉴>");
        Map<String, Integer> orderMenus = OrderController.getOrders(orders);

        orderMenus.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .forEach(System.out::println);
        System.out.println();
    }
}