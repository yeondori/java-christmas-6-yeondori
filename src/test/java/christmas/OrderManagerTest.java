package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

class OrderManagerTest {
    @DisplayName("메뉴판에 있는 메뉴는 주문이 가능하다")
    @Test
    void validateMenuTrueCase() {
        OrderManager orderManager = OrderManager.from(MenuBoard.loadMenu());
        HashMap<String, Integer> orderInput = new HashMap<>();

        orderInput.put("양송이수프", 1);
        orderInput.put("티본스테이크", 2);

        orderManager.receiveOrders(orderInput);

        assertThat(orderManager.getOrders().size()).isEqualTo(2);
    }

    @DisplayName("메뉴판에 없는 메뉴는 주문할 수 없다")
    @Test
    void validateMenuFalseCase() {
        OrderManager orderManager = OrderManager.from(MenuBoard.loadMenu());
        HashMap<String, Integer> orderInput = new HashMap<>();

        orderInput.put("양송이수프", 1);
        orderInput.put("뿌링클", 2);

        assertThatThrownBy(() -> orderManager.receiveOrders(orderInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}