package christmas.order;

import christmas.menu.Category;
import christmas.menu.Menu;
import christmas.menu.MenuBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrdersTest {
    MenuBoard menuBoard = new MenuBoard(Arrays.asList(
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

    private final int MAX_QUANTITY = 20;

    @DisplayName("총 주문가능 수량(20)을 초과하는 경우 예외 처리한다.")
    @Test
    void receiveOrdersMoreThanMaxQuantity() {
        Map<String, Integer> orderInput = new HashMap<>();
        orderInput.put("양송이수프", 1);
        orderInput.put("티본스테이크", MAX_QUANTITY);

        assertThatThrownBy(() -> Orders.receiveOrders(menuBoard, orderInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문하는 경우 예외 처리한다.")
    @Test
    void receiveOrdersOnlyDrinks() {
        Map<String, Integer> orderInput = new HashMap<>();
        orderInput.put("제로콜라", 1);
        orderInput.put("샴페인", 2);

        assertThatThrownBy(() -> Orders.receiveOrders(menuBoard, orderInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문내역을 통해 총금액을 반환한다.")
    @Test
    void getTotalPrice() {
        Map<String, Integer> orderInput = new HashMap<>();
        orderInput.put("양송이수프", 1);
        orderInput.put("제로콜라", 2);

        int expectedPrice = menuBoard.findPrice("양송이수프") * 1
                + menuBoard.findPrice("제로콜라") * 2;

        Orders orders = Orders.receiveOrders(menuBoard, orderInput);

        assertThat(orders.getTotalPrice()).isEqualTo(expectedPrice);
    }

    @DisplayName("주문한 메뉴의 카테고리별 총 주문 금액을 반환한다")
    @Test
    void getQuantityByCategory() {
        Map<String, Integer> orderInput = new HashMap<>();
        orderInput.put("제로콜라", 3);
        orderInput.put("초코케이크", 1);
        orderInput.put("아이스크림", 2);

        Orders orders = Orders.receiveOrders(menuBoard, orderInput);
        Map<Category, Integer> result = orders.getQuantityByCategory();

        assertThat(result).containsExactlyInAnyOrderEntriesOf(Map.of(
                Category.APPETIZER, 0,
                Category.MAIN, 0,
                Category.DESSERT, 3,
                Category.DRINK, 3
        ));
    }
}