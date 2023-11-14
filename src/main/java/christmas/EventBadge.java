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
}