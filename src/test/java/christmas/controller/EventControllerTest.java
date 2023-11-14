package christmas.controller;

import christmas.discount.DiscountPolicy;
import christmas.event.EventBadge;
import christmas.menu.Category;
import christmas.menu.Menu;
import christmas.menu.MenuBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class EventControllerTest {
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
        EventBadge eventBadge = EventController.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.NONE);
    }

    @DisplayName("총혜택금액이 5천원 이상 1만원 미만이면 배지 '별'이다.")
    @Test
    void getEventBadgeIfBenefitUnder10000() {
        int totalBenefitPrice = TreeThreshold - 1;
        EventBadge eventBadge = EventController.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.STAR);
    }

    @DisplayName("총혜택금액이 1만원 이상 2만원 미만이면 배지 '트리'이다.")
    @Test
    void getEventBadgeIfBenefitUnder20000() {
        int totalBenefitPrice = SantaThreshold - 1;
        EventBadge eventBadge = EventController.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.TREE);
    }

    @DisplayName("총혜택금액이 2만원 이상이면 배지 '산타'이다.")
    @Test
    void getEventBadgeIfBenefitOver20000() {
        int totalBenefitPrice = SantaThreshold;
        EventBadge eventBadge = EventController.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.SANTA);
    }

    @DisplayName("주문내역과 주문일자를 입력하면 총혜택금액을 반환한다.")
    @Test
    void getTotalBenefitPrice() {
        OrderController orderController = createTestOrders();

        EventController eventController = EventController.from(orderController, 25);
        int actualDiscount = eventController.getTotalBenefitPrice();

        int expectedDiscount = calculateExpectedDiscount(orderController, 25);
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }

    private OrderController createTestOrders() {
        Map<String, Integer> orderInput = new HashMap<>();
        orderInput.put("티본스테이크", 2);
        orderInput.put("초코케이크", 1);

        OrderController orderController = OrderController.receiveOrders(menuBoard, orderInput);
        return orderController;
    }

    private int calculateExpectedDiscount(OrderController orderController, int date) {
        DiscountPolicy discountPolicy = new DiscountPolicy();
        Map<Category, Integer> quantityByCategory = orderController.getQuantityByCategory();
        int totalPrice = orderController.getTotalPrice();

        int christmasDiscount = discountPolicy.getChristmasDiscount(totalPrice, date);
        int specialDayDiscount = discountPolicy.getSpecialDayDiscount(totalPrice, date);
        int weekdayDiscount = discountPolicy.getWeekdayDiscount(totalPrice, date, quantityByCategory.get(Category.DESSERT));
        int weekendDiscount = discountPolicy.getWeekendDiscount(totalPrice, date, quantityByCategory.get(Category.MAIN));
        int giftDiscount = discountPolicy.getGiftDiscount(totalPrice);

        return christmasDiscount + specialDayDiscount + weekdayDiscount + weekendDiscount + giftDiscount;
    }
}