package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountPolicyTest {
    private final List<Integer> specialDay = Arrays.asList(3, 10, 17, 24, 25, 31);

    @DisplayName("크리스마스 기간에는 크리스마스 할인을 받을 수 있다.")
    @Test
    void getChristmasDiscountDuringChristmas() {
        DiscountPolicy discountPolicy = new DiscountPolicy();
        int christmasDiscount = discountPolicy.getChristmasDiscount(25);

        assertThat(christmasDiscount).isEqualTo(3400);
    }

    @DisplayName("크리스마스가 지나면 크리스마스 할인을 받을 수 없다.")
    @Test
    void getNoChristmasDiscountAfterChristmas() {
        DiscountPolicy discountPolicy = new DiscountPolicy();
        int christmasDiscount = discountPolicy.getChristmasDiscount(26);

        assertThat(christmasDiscount).isEqualTo(0);
    }

    @DisplayName("특정 날짜에는 특별 할인을 받을 수 있다")
    @Test
    void getSpecialDiscountOnSpecialDay() {
        DiscountPolicy discountPolicy = new DiscountPolicy();

        for (int day : specialDay) {
            int specialDiscount = discountPolicy.getSpecialDayDiscount(day);
            assertThat(specialDiscount).isEqualTo(1000);
        }
    }

    @DisplayName("지정한 날짜가 아니면 특별 할인을 받을 수 없다")
    @Test
    void getNoSpecialDiscountOnNonSpecialDay() {
        DiscountPolicy discountPolicy = new DiscountPolicy();

        int specialDiscount = discountPolicy.getSpecialDayDiscount(1);

        assertThat(specialDiscount).isEqualTo(0);
    }
}