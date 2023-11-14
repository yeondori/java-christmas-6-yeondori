package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventControllerTest {
    private final int StarThreshold = 5000;
    private final int TreeThreshold = 5000;
    private final int SantaThreshold = 5000;

    @DisplayName("총혜택금액이 5천원 미만이면 배지 미대상이다.")
    @Test
    void getEventBadgeIfBenefitUnder5000() {
        int totalBenefitPrice = SantaThreshold - 1;
        EventBadge eventBadge = EventController.getEventBadge(totalBenefitPrice);

        assertThat(eventBadge).isEqualTo(EventBadge.미대상);
    }

    @Test
    void getTotalBenefitPrice() {
    }
}