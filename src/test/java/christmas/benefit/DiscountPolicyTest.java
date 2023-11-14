package christmas.benefit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountPolicyTest {
    private final List<Integer> specialDay = Arrays.asList(3, 10, 17, 24, 25, 31);
    private final int WEEKDAY = 3;
    private final int WEEKEND = 2;

    private final int MIN_ORDER_AMOUNT_FOR_DISCOUNT = 10000;

    @DisplayName("크리스마스 기간에는 크리스마스 할인을 받을 수 있다.")
    @Test
    void getChristmasDiscountDuringChristmas() {
        DiscountPolicy discountPolicy = new DiscountPolicy();
        int christmasDiscount = discountPolicy.getChristmasDiscount(MIN_ORDER_AMOUNT_FOR_DISCOUNT, 25);

        assertThat(christmasDiscount).isEqualTo(3400);
    }

    @DisplayName("크리스마스가 지나면 크리스마스 할인을 받을 수 없다.")
    @Test
    void getNoChristmasDiscountAfterChristmas() {
        DiscountPolicy discountPolicy = new DiscountPolicy();
        int christmasDiscount = discountPolicy.getChristmasDiscount(MIN_ORDER_AMOUNT_FOR_DISCOUNT, 26);

        assertThat(christmasDiscount).isEqualTo(0);
    }

    @DisplayName("특정 날짜에는 특별 할인을 받을 수 있다.")
    @Test
    void getSpecialDiscountOnSpecialDay() {
        DiscountPolicy discountPolicy = new DiscountPolicy();

        for (int day : specialDay) {
            int specialDiscount = discountPolicy.getSpecialDayDiscount(MIN_ORDER_AMOUNT_FOR_DISCOUNT, day);
            assertThat(specialDiscount).isEqualTo(1000);
        }
    }

    @DisplayName("특정 날짜가 아니면 특별 할인을 받을 수 없다.")
    @Test
    void getNoSpecialDiscountOnNonSpecialDay() {
        DiscountPolicy discountPolicy = new DiscountPolicy();

        int specialDiscount = discountPolicy.getSpecialDayDiscount(MIN_ORDER_AMOUNT_FOR_DISCOUNT, 1);
        assertThat(specialDiscount).isEqualTo(0);

    }

    @DisplayName("평일에 디저트를 주문하면 평일 할인을 받을 수 있다.")
    @Test
    void getWeekdayDiscountOnDessertOrder() {
        DiscountPolicy discountPolicy = new DiscountPolicy();

        int dessertQuantity = 1;
        int weekdayDiscount = discountPolicy.getWeekdayDiscount(MIN_ORDER_AMOUNT_FOR_DISCOUNT, WEEKDAY, dessertQuantity);

        assertThat(weekdayDiscount).isEqualTo(2023 * dessertQuantity);
    }

    @DisplayName("주말에 메인을 주문하면 주말 할인을 받을 수 있다.")
    @Test
    void getWeekendDiscountOnMainOrder() {
        DiscountPolicy discountPolicy = new DiscountPolicy();

        int mainQuanitty = 2;
        int weekendDiscount = discountPolicy.getWeekendDiscount(MIN_ORDER_AMOUNT_FOR_DISCOUNT, WEEKEND, mainQuanitty);

        assertThat(weekendDiscount).isEqualTo(2023 * mainQuanitty);
    }

    @DisplayName("12만원 이상 구매시 증정 할인을 받을 수 있다.")
    @Test
    void getGiftDiscountIfExceeds120000() {
        DiscountPolicy discountPolicy = new DiscountPolicy();

        int weekendDiscount = discountPolicy.getGiftDiscount(120_000);

        assertThat(weekendDiscount).isEqualTo(25000);
    }

    @DisplayName("12만원 미만 구매시 증정 할인을 받을 수 없다.")
    @Test
    void getNoGiftDiscountIfNotExceeds120000() {
        DiscountPolicy discountPolicy = new DiscountPolicy();

        int weekendDiscount = discountPolicy.getGiftDiscount(119_000);

        assertThat(weekendDiscount).isEqualTo(0);
    }
}