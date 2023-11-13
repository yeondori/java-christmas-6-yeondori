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
        if (additionalDiscount == null) {
            throw new IllegalArgumentException("[ERROR] 추가 할인 대상이 아닙니다.");
        }
        return additionalDiscount;
    }

    public int getPriceCondition() {
        if (priceCondition == null) {
            throw new IllegalArgumentException("[ERROR] 주문 금액이 필요한 할인 대상이 아닙니다.");
        }
        return priceCondition;
    }
}