package christmas.ui;

import christmas.discount.Discount;
import christmas.event.EventController;
import christmas.order.Order;
import christmas.order.OrderController;
import org.w3c.dom.ls.LSOutput;

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
        String formattedPrice = String.format("%,d원", OrderController.getTotalPrice(orders));
        System.out.println(formattedPrice);
    }

    public void printBenefits(List<Order> orders, int date) {
        EventController eventController = EventController.from(orders);
        Map<Discount, Integer> totalBenefits = eventController.getTotalBenefits(date);

        printGift(totalBenefits);
        printBenefitsDetail(totalBenefits);

    }

    private static void printBenefitsDetail(Map<Discount, Integer> totalBenefits) {
        System.out.println("<혜택 내역>");
        boolean hasDiscount = false;

        for (Map.Entry<Discount, Integer> entry : totalBenefits.entrySet()) {
            int discountAmount = entry.getValue();

            if (discountAmount != 0) {
                hasDiscount = true;

                System.out.printf("%s 할인: %,d원%n", entry.getKey().name(), discountAmount);
            }
        }

        if (!hasDiscount) {
            System.out.println("없음");
        }
    }

    private static void printGift(Map<Discount, Integer> totalBenefits) {
        System.out.println("\n<증정 메뉴>");

        Integer giftDiscount = totalBenefits.get(Discount.GIFT);
        if (giftDiscount != 0) {
            System.out.println("샴페인 1개");
        }
        if (giftDiscount == 0) {
            System.out.println("없음");
        }
    }

}