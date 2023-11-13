package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @DisplayName("메뉴에 없는 음식을 주문하면 예외 처리한다.")
    @Test
    void createOrderOfWrongMenu() {
        assertThatThrownBy(() -> Order.createOrderOf("뿌링클", 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void calculateOrderPrice() {
    }

    @Test
    void getCategory() {
    }

    @Test
    void getMenuName() {
    }

    @Test
    void getQuantity() {
    }
}