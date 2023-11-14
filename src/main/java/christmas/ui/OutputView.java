package christmas.ui;

import christmas.benefit.BenefitManager;
import christmas.benefit.Discount;
import christmas.benefit.EventBadge;
import christmas.order.Orders;

import java.util.Map;

public class OutputView {

    private final Orders orders;
    private final BenefitManager benefitManager;

    public OutputView(Orders orders, BenefitManager benefitManager) {
        this.orders = orders;
        this.benefitManager = benefitManager;
    }

    public void printResult() {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", benefitManager.getDate());

        printOrder();
        printBenefits();
        printTotalPayment();
        printEventBadge();
    }

    public void printOrder() {
        System.out.println("\n<주문 메뉴>");
        Map<String, Integer> orderMenus = orders.getOrderHistory();

        orderMenus.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .forEach(System.out::println);

        System.out.println("\n<할인 전 총주문 금액>");
        System.out.printf("%,d원%n", orders.getTotalPrice());
    }

    public void printBenefits() {
        Map<Discount, Integer> totalBenefits = benefitManager.getTotalBenefits(orders);

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
        int totalBenefitPrice = benefitManager.getTotalBenefitPrice(orders);

        if (totalBenefitPrice != 0) {
            System.out.printf("-");
        }
        System.out.printf("%,d원%n", totalBenefits.values().stream().reduce(0, Integer::sum));
    }

    public void printTotalPayment() {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.printf("%,d원%n", benefitManager.getTotalPayment(orders));
    }

    public void printEventBadge() {
        int totalBenefitPrice = benefitManager.getTotalBenefitPrice(orders);
        EventBadge eventBadge = benefitManager.getEventBadge(totalBenefitPrice);

        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(eventBadge.getValue());
    }
}