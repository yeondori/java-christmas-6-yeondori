package christmas.benefit;

import christmas.menu.Category;
import christmas.menu.Menu;
import christmas.menu.MenuBoard;
import christmas.order.Orders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BenefitManagerTest {
    private final MenuBoard menuBoard = new MenuBoard(Arrays.asList(
            new Menu("양송이수프", 6_000, Category.APPETIZER),
            new Menu("타파스", 5_500, Category.APPETIZER),
            new Menu("시저샐러드", 8_000, Category.APPETIZER),
            new Menu("티본스테이크", 55_000, Category.MAIN),
            new Menu("바비큐립", 54_000, Category.MAIN),
            new Menu("해산물파스타", 35_000, Category.MAIN),
            new Menu("크리스마스파스타", 25_000, Category.MAIN),
            new Menu("초코케이크", 15_000, Category.DESSERT),
            new Menu("아이스크림", 5_000, Category.DESSERT),
            new Menu("제로콜라", 3_000, Category.DRINK),
            new Menu("레드와인", 60_000, Category.DRINK),
            new Menu("샴페인", 25_000, Category.DRINK)
    ));

    private final int StarThreshold = 5000;
    private final int TreeThreshold = 10000;
    private final int SantaThreshold = 20000;

    @DisplayName("총혜택금액이 5천원 미만이면 배지 '없음'이다.")
    @Test
    void getEventBadgeIfBenefitUnder5000() {
        int totalBenefitPrice = StarThreshold - 1;
        EventBadge eventBadge = BenefitManager.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.NONE);
    }

    @DisplayName("총혜택금액이 5천원 이상 1만원 미만이면 배지 '별'이다.")
    @Test
    void getEventBadgeIfBenefitUnder10000() {
        int totalBenefitPrice = TreeThreshold - 1;
        EventBadge eventBadge = BenefitManager.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.STAR);
    }

    @DisplayName("총혜택금액이 1만원 이상 2만원 미만이면 배지 '트리'이다.")
    @Test
    void getEventBadgeIfBenefitUnder20000() {
        int totalBenefitPrice = SantaThreshold - 1;
        EventBadge eventBadge = BenefitManager.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.TREE);
    }

    @DisplayName("총혜택금액이 2만원 이상이면 배지 '산타'이다.")
    @Test
    void getEventBadgeIfBenefitOver20000() {
        int totalBenefitPrice = SantaThreshold;
        EventBadge eventBadge = BenefitManager.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.SANTA);
    }

    @DisplayName("주문내역과 주문일자를 입력하면 총혜택금액을 반환한다.")
    @Test
    void getTotalBenefitPrice() {
        Orders orders = createTestOrders();

        BenefitManager benefitManager = BenefitManager.from(25);
        int actualDiscount = benefitManager.getTotalBenefitPrice(orders);

        int expectedDiscount = calculateExpectedDiscount(orders, 25);
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }

    @DisplayName("주문내역과 주문일자를 입력하면 총결제금액을 반환한다.")
    @Test
    void getTotalPayment() {
        Orders orders = createTestOrders();

        BenefitManager benefitManager = BenefitManager.from(25);

        int totalPrice = orders.getTotalPrice();
        int discount = benefitManager.getTotalBenefitPrice(orders);
        int giftBenefit = benefitManager.getTotalBenefits(orders).get(Discount.GIFT);

        int expectedTotalPayment = totalPrice - discount + giftBenefit;

        assertThat(benefitManager.getTotalPayment(orders)).isEqualTo(expectedTotalPayment);
    }

    private Orders createTestOrders() {
        Map<String, Integer> orderInput = new HashMap<>();
        orderInput.put("티본스테이크", 2);
        orderInput.put("초코케이크", 1);

        Orders orders = Orders.receiveOrders(menuBoard, orderInput);
        return orders;
    }

    private int calculateExpectedDiscount(Orders orders, int date) {
        DiscountPolicy discountPolicy = new DiscountPolicy();
        Map<Category, Integer> quantityByCategory = orders.getQuantityByCategory();
        int totalPrice = orders.getTotalPrice();

        int christmasDiscount = discountPolicy.getChristmasDiscount(totalPrice, date);
        int specialDayDiscount = discountPolicy.getSpecialDayDiscount(totalPrice, date);
        int weekdayDiscount = discountPolicy.getWeekdayDiscount(totalPrice, date, quantityByCategory.get(Category.DESSERT));
        int weekendDiscount = discountPolicy.getWeekendDiscount(totalPrice, date, quantityByCategory.get(Category.MAIN));
        int giftDiscount = discountPolicy.getGiftDiscount(totalPrice);

        return christmasDiscount + specialDayDiscount + weekdayDiscount + weekendDiscount + giftDiscount;
    }
}