package christmas.controller;

import christmas.menu.Category;
import christmas.menu.MenuBoard;
import christmas.controller.OrderController;
import christmas.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderControllerTest {

    private final int MAX_QUANTITY = 20;

    @DisplayName("총 주문가능 수량(20)을 초과하는 경우 예외 처리한다.")
    @Test
    void receiveOrdersMoreThanMaxQuantity() {
        Map<String, Integer> orderInput = new HashMap<>();
        orderInput.put("양송이수프", 1);
        orderInput.put("티본스테이크", MAX_QUANTITY);

        assertThatThrownBy(() -> OrderController.receiveOrders(orderInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문하는 경우 예외 처리한다.")
    @Test
    void receiveOrdersOnlyDrinks() {
        Map<String, Integer> orderInput = new HashMap<>();
        orderInput.put("제로콜라", 1);
        orderInput.put("샴페인", 2);

        assertThatThrownBy(() -> OrderController.receiveOrders(orderInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문내역을 통해 총금액을 반환한다.")
    @Test
    void getTotalPrice() {
        Map<String, Integer> orderInput = new HashMap<>();
        orderInput.put("양송이수프", 1);
        orderInput.put("제로콜라", 2);

        int expectedPrice = MenuBoard.loadMenu().findPrice("양송이수프") * 1
                + MenuBoard.loadMenu().findPrice("제로콜라") * 2;

        OrderController orderController = OrderController.receiveOrders(orderInput);

        assertThat(orderController.getTotalPrice()).isEqualTo(expectedPrice);
    }

    @DisplayName("주문한 메뉴의 카테고리별 총 주문 금액을 반환한다")
    @Test
    void getQuantityByCategory() {
        Map<String, Integer> orderInput = new HashMap<>();
        orderInput.put("제로콜라", 3);
        orderInput.put("초코케이크", 1);
        orderInput.put("아이스크림", 2);

        OrderController orderController = OrderController.receiveOrders(orderInput);
        Map<Category, Integer> result = orderController.getQuantityByCategory();

        assertThat(result).containsExactlyInAnyOrderEntriesOf(Map.of(
                Category.APPETIZER, 0,
                Category.MAIN, 0,
                Category.DESSERT, 3,
                Category.DRINK, 3
        ));;
    }
}