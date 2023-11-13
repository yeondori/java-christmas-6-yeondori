package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class OrderControllerTest {

    @DisplayName("총주문수량이 20을 초과하는 경우 예외 처리한다.")
    @Test
    void receiveOrdersMoreThanMaxQuantity() {
        Map<String, Integer> orderInput = new HashMap<>();
        orderInput.put("양송이수프", 1);
        orderInput.put("티본스테이크", 20);

        assertThatThrownBy(() -> OrderController.receiveOrders(orderInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getTotalPrice() {
    }
}