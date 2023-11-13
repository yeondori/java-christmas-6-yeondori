package christmas;

public enum EventBadge {
    미대상(null),
    별(5_000),
    트리(10_000),
    산타(20_000);

    private final Integer benefitPrice;

    EventBadge(Integer benefitPrice) {
        this.benefitPrice = benefitPrice;
    }

    public static EventBadge getEventBadge(int totalBenefitPrice) {
        if (totalBenefitPrice < 별.benefitPrice) {
            return 미대상;
        }
        if (totalBenefitPrice < 트리.benefitPrice) {
            return 별;
        }
        if (totalBenefitPrice < 산타.benefitPrice) {
            return 트리;
        }
        return 산타;
    }
}