package christmas.event;

public enum EventBadge {
    없음(null),
    별(5_000),
    트리(10_000),
    산타(20_000);

    private final Integer benefitPrice;

    EventBadge(Integer benefitPrice) {
        this.benefitPrice = benefitPrice;
    }

    public Integer getBenefitPrice() {
        return benefitPrice;
    }
}