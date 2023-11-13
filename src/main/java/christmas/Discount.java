package christmas;

public enum Discount {
    CHRISTMAS(1000, 100),
    SPECIAL(1000, 0),
    WEEKDAY(2023, 0),
    WEEKEND(2023, 0),
    GIFT(25000, 0);

    private final int baseDiscount;
    private final int additionalDiscount;

    Discount(int baseDiscount, int additionalDiscount) {
        this.baseDiscount = baseDiscount;
        this.additionalDiscount = additionalDiscount;
    }

    public int getBaseDiscount() {
        return baseDiscount;
    }

    public int getAdditionalDiscount() {
        return additionalDiscount;
    }
}
