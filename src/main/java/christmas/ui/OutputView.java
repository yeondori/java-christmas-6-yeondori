package christmas.ui;

import christmas.discount.Discount;
import christmas.event.EventBadge;
import christmas.event.EventController;
import christmas.order.Order;
import christmas.order.OrderController;

import java.util.List;
import java.util.Map;

import static christmas.event.EventBadge.미대상;

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
        System.out.printf("%,d원%n", OrderController.getTotalPrice(orders));
    }

    public void printBenefits(List<Order> orders, int date) {
        EventController eventController = EventController.from(orders);
        Map<Discount, Integer> totalBenefits = eventController.getTotalBenefits(date);

        printGift(totalBenefits);
        printBenefitsDetail(totalBenefits);
    }

    public void printTotalPayment(List<Order> orders, int date) {
        EventController eventController = EventController.from(orders);

        int totalPrice = OrderController.getTotalPrice(orders);
        int totalBenefitPrice = eventController.getTotalBenefitPrice(date);

        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.printf("%,d원%n", totalPrice - totalBenefitPrice);
    }

    public void printEventBadge(List<Order> orders, int date) {
        EventController eventController = EventController.from(orders);

        int totalBenefitPrice = eventController.getTotalBenefitPrice(date);
        EventBadge eventBadge = EventController.getEventBadge(totalBenefitPrice);

        System.out.println("<12월 이벤트 배지>");
        if (eventBadge==미대상) {
            System.out.println("없음");
        }
        System.out.println(eventBadge.name());
    }

    private static void printBenefitsDetail(Map<Discount, Integer> totalBenefits) {
        System.out.println("\n<혜택 내역>");

        totalBenefits.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .forEach(entry -> System.out.printf("%s: -%,d원%n", entry.getKey().getDescription(), entry.getValue()));

        if (totalBenefits.values().stream().allMatch(value -> value == 0)) {
            System.out.println("없음");
        }

        System.out.println("\n<총혜택 금액>");
        System.out.printf("-%,d원%n", totalBenefits.values().stream().reduce(0, Integer::sum));
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