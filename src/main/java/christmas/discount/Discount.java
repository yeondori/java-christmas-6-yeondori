package christmas.discount;

public enum Discount {
    CHRISTMAS("크리스마스 디데이 할인", 1000, 100, 10000),
    SPECIAL("특별 할인", 1000, null, 10000),
    WEEKDAY("평일 할인", 2023, null, 10000),
    WEEKEND("주말 할인", 2023, null, 10000),
    GIFT("증정 이벤트", 25000, null, 120000);

    private final String description;
    private final int baseDiscount;
    private final Integer additionalDiscount;
    private final Integer priceCondition;

    Discount(String descript, int baseDiscount, Integer additionalDiscount, Integer priceCondition) {
        this.description = descript;
        this.baseDiscount = baseDiscount;
        this.additionalDiscount = additionalDiscount;
        this.priceCondition = priceCondition;
    }

    public String getDescription() {
        return description;
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