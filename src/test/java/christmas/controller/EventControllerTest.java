package christmas.controller;

import christmas.discount.DiscountPolicy;
import christmas.controller.EventController;
import christmas.event.EventBadge;
import christmas.menu.Category;
import christmas.order.Order;
import christmas.controller.OrderController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class EventControllerTest {
    private final int StarThreshold = 5000;
    private final int TreeThreshold = 10000;
    private final int SantaThreshold = 20000;

    @DisplayName("총혜택금액이 5천원 미만이면 배지 '미대상'이다.")
    @Test
    void getEventBadgeIfBenefitUnder5000() {
        int totalBenefitPrice = StarThreshold - 1;
        EventBadge eventBadge = EventController.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.미대상);
    }

    @DisplayName("총혜택금액이 5천원 이상 1만원 미만이면 배지 '별'이다.")
    @Test
    void getEventBadgeIfBenefitUnder10000() {
        int totalBenefitPrice = TreeThreshold - 1;
        EventBadge eventBadge = EventController.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.별);
    }

    @DisplayName("총혜택금액이 1만원 이상 2만원 미만이면 배지 '트리'이다.")
    @Test
    void getEventBadgeIfBenefitUnder20000() {
        int totalBenefitPrice = SantaThreshold - 1;
        EventBadge eventBadge = EventController.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.트리);
    }

    @DisplayName("총혜택금액이 2만원 이상이면 배지 '산타'이다.")
    @Test
    void getEventBadgeIfBenefitOver20000() {
        int totalBenefitPrice = SantaThreshold;
        EventBadge eventBadge = EventController.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.산타);
    }

    @DisplayName("주문내역과 주문일자를 입력하면 총혜택금액을 반환한다.")
    @Test
    void getTotalBenefitPrice() {
        List<Order> orders = createTestOrders();

        EventController eventController = EventController.from(orders, 25);
        int actualDiscount = eventController.getTotalBenefitPrice();

        int expectedDiscount = calculateExpectedDiscount(orders, 25);
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }

    private List<Order> createTestOrders() {
        Map<String, Integer> orderInput = new HashMap<>();
        orderInput.put("티본스테이크", 2);
        orderInput.put("초코케이크", 1);

        return OrderController.receiveOrders(orderInput);
    }

    private int calculateExpectedDiscount(List<Order> orders, int date) {
        DiscountPolicy discountPolicy = new DiscountPolicy();
        Map<Category, Integer> quantityByCategory = OrderController.getQuantityByCategory(orders);

        int christmasDiscount = discountPolicy.getChristmasDiscount(date);
        int specialDayDiscount = discountPolicy.getSpecialDayDiscount(date);
        int weekdayDiscount = discountPolicy.getWeekdayDiscount(date, quantityByCategory.get(Category.DESSERT));
        int weekendDiscount = discountPolicy.getWeekendDiscount(date, quantityByCategory.get(Category.MAIN));
        int giftDiscount = discountPolicy.getGiftDiscount(OrderController.getTotalPrice(orders));

        return christmasDiscount + specialDayDiscount + weekdayDiscount + weekendDiscount + giftDiscount;
    }
}