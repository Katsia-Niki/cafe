package by.jwd.cafe.entity;

public enum PaymentType {
    ACCOUNT(15),
    CASH(10),
    LOYALTY_POINTS(0);

    private int percentLoyaltyPoints;

    PaymentType(int percent) {
        percentLoyaltyPoints = percent;
    }

    public int getPercentLoyaltyPoints() {
        return percentLoyaltyPoints;
    }
}
