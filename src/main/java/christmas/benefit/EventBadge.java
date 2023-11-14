package christmas.benefit;

public enum EventBadge {
    NONE("없음", null),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String value;
    private final Integer benefitPrice;

    EventBadge(String value, Integer benefitPrice) {
        this.value = value;
        this.benefitPrice = benefitPrice;
    }

    public String getValue() {
        return value;
    }

    public Integer getBenefitPrice() {
        return benefitPrice;
    }
}