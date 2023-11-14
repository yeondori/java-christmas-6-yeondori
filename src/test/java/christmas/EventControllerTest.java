package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventControllerTest {
    private final int StarThreshold = 5000;
    private final int TreeThreshold = 10000;
    private final int SantaThreshold = 20000;

    @DisplayName("총혜택금액이 5천원 미만이면 배지 '미대상'이다.")
    @Test
    void getEventBadgeIfBenefitUnder5000() {
        int totalBenefitPrice = StarThreshold - 1;
        EventBadge eventBadge = EventController.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.미대상);
    }

    @DisplayName("총혜택금액이 5천원 이상 1만원 미만이면 배지 '별'이다.")
    @Test
    void getEventBadgeIfBenefitUnder10000() {
        int totalBenefitPrice = TreeThreshold - 1;
        EventBadge eventBadge = EventController.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.별);
    }

    @DisplayName("총혜택금액이 1만원 이상 2만원 미만이면 배지 '트리'이다.")
    @Test
    void getEventBadgeIfBenefitUnder20000() {
        int totalBenefitPrice = SantaThreshold - 1;
        EventBadge eventBadge = EventController.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.트리);
    }

    @DisplayName("총혜택금액이 2만원 이상이면 배지 '산타'이다.")
    @Test
    void getEventBadgeIfBenefitOver20000() {
        int totalBenefitPrice = SantaThreshold;
        EventBadge eventBadge = EventController.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.산타);
    }

    @Test
    void getTotalBenefitPrice() {
    }
}