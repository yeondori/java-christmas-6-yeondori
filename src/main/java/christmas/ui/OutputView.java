package christmas.ui;

import christmas.controller.EventController;
import christmas.controller.OrderController;
import christmas.discount.Discount;
import christmas.event.EventBadge;

import java.util.Map;

public class OutputView {

    private final OrderController orderController;
    private final EventController eventController;

    private OutputView(OrderController orderController, EventController eventController) {
        this.orderController = orderController;
        this.eventController = eventController;
    }

    public static OutputView of(OrderController orderController, EventController eventController) {
        return new OutputView(orderController, eventController);
    }

    public void printResult() {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", eventController.getDate());

        printOrder();
        printBenefits();
        printTotalPayment();
        printEventBadge();
    }

    public void printOrder() {
        System.out.println("\n<주문 메뉴>");
        Map<String, Integer> orderMenus = orderController.getOrderHistory();

        orderMenus.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .forEach(System.out::println);

        System.out.println("\n<할인 전 총주문 금액>");
        System.out.printf("%,d원%n", eventController.getOrderPrice());
    }

    public void printBenefits() {
        Map<Discount, Integer> totalBenefits = eventController.getTotalBenefits();

        printGift(totalBenefits);
        printBenefitsDetail(totalBenefits);
    }

    private void printGift(Map<Discount, Integer> totalBenefits) {
        System.out.println("\n<증정 메뉴>");

        Integer giftDiscount = totalBenefits.get(Discount.GIFT);
        if (giftDiscount != 0) {
            System.out.println("샴페인 1개");
        }
        if (giftDiscount == 0) {
            System.out.println("없음");
        }
    }

    private void printBenefitsDetail(Map<Discount, Integer> totalBenefits) {
        System.out.println("\n<혜택 내역>");

        totalBenefits.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .forEach(entry -> System.out.printf("%s: -%,d원%n", entry.getKey().getDescription(), entry.getValue()));

        if (totalBenefits.values().stream().allMatch(value -> value == 0)) {
            System.out.println("없음");
        }

        printTotalBenefitPrice(totalBenefits);
    }

    private void printTotalBenefitPrice(Map<Discount, Integer> totalBenefits) {
        System.out.println("\n<총혜택 금액>");
        int totalBenefitPrice = eventController.getTotalBenefitPrice();

        if (totalBenefitPrice!=0) {
            System.out.printf("-");
        }
        System.out.printf("%,d원%n", totalBenefits.values().stream().reduce(0, Integer::sum));
    }

    public void printTotalPayment() {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.printf("%,d원%n", eventController.getTotalPayment());
    }

    public void printEventBadge() {
        int totalBenefitPrice = eventController.getTotalBenefitPrice();
        EventBadge eventBadge = eventController.getEventBadge(totalBenefitPrice);

        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(eventBadge.name());
    }
}