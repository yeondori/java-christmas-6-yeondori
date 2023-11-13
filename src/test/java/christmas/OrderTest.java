package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    @DisplayName("메뉴에 없는 음식을 주문하면 예외 처리한다.")
    @Test
    void createOrderOfWrongMenu() {
        assertThatThrownBy(() -> Order.createOrderOf("뿌링클", 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1개 미만으로 주문하면 예외 처리한다.")
    @Test
    void createOrderOfOrderQunatityZero() {
        assertThatThrownBy(() -> Order.createOrderOf("양송이수프", 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("20개를 초과 주문하면 예외 처리한다.")
    @Test
    void createOrderOfOrderQunatityMoreThan20() {
        assertThatThrownBy(() -> Order.createOrderOf("양송이수프", 21))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void calculateOrderPrice() {
        Order 양송이수프 = Order.createOrderOf("양송이수프", 3);
        int orderPrice = Order.calculateOrderPrice(양송이수프);

        assertThat(orderPrice).isEqualTo(양송이수프.getPrice() * 3);
    }
}