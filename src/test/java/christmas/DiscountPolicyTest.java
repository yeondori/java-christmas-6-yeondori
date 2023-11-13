package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountPolicyTest {
    private final List<Integer> specialDay = Arrays.asList(3, 10, 17, 24, 25, 31);
    private final List<Integer> weekend = Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);

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

    @DisplayName("평일에 디저트를 주문하면 평일 할인을 받을 수 있다")
    @Test
    void getWeekdayDiscountOnDessertOrder() {
        DiscountPolicy discountPolicy = new DiscountPolicy();

        int date = 3;
        int dessertQuantity = 1;
        int weekdayDiscount = discountPolicy.getWeekdayDiscount(date, dessertQuantity);

        assertThat(weekdayDiscount).isEqualTo(2023);
    }
}