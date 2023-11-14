package christmas.ui;

import christmas.discount.Discount;
import christmas.event.EventController;
import christmas.order.Order;
import christmas.order.OrderController;

import java.util.List;
import java.util.Map;

public class OutputView {
    public void printResult() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printOrder(List<Order> orders) {
        System.out.println("\n<주문 메뉴>");
        Map<String, Integer> orderMenus = OrderController.getOrders(orders);

        orderMenus.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .forEach(System.out::println);

        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(OrderController.getTotalPrice(orders));
    }

    public void printBenefits(List<Order> orders, int date) {
        System.out.println("\n<증정 메뉴>");

        EventController eventController = EventController.from(orders);
        Map<Discount, Integer> totalBenefits = eventController.getTotalBenefits(date);

        printGift(totalBenefits);
    }

    private static void printGift(Map<Discount, Integer> totalBenefits) {
        Integer giftDiscount = totalBenefits.get(Discount.GIFT);
        if (giftDiscount != 0) {
            System.out.println("샴페인 1개");
        }
        if (giftDiscount == 0) {
            System.out.println("없음");
        }
    }

}