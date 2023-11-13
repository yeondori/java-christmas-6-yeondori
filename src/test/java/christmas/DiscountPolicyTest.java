package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountPolicyTest {
    @DisplayName("크리스마스 기간에는 크리스마스 할인을 받을 수 있다.")
    @Test
    void getChristmasDiscountDuringChristmas() {
        DiscountPolicy discountPolicy = new DiscountPolicy();
        int christmasDiscount = discountPolicy.getChristmasDiscount(25);

        assertThat(christmasDiscount).isEqualTo(3400);
    }
}