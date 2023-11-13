package christmas;

public enum Discount {
    CHRISTMAS(1000, 100, null),
    SPECIAL(1000, null, null),
    WEEKDAY(2023, null, null),
    WEEKEND(2023, null, null),
    GIFT(25000, null, 120000);

    private final int baseDiscount;
    private final Integer additionalDiscount;
    private final Integer priceCondition;

    Discount(int baseDiscount, Integer additionalDiscount, Integer priceCondition) {
        this.baseDiscount = baseDiscount;
        this.additionalDiscount = additionalDiscount;
        this.priceCondition = priceCondition;
    }

    public int getBaseDiscount() {
        return baseDiscount;
    }

    public int getAdditionalDiscount() {
        return additionalDiscount;
    }

    public int getPriceCondition() {
        return priceCondition;
    }
}