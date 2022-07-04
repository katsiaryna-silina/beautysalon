package by.silina.beautysalon.model.entity;

import java.math.BigDecimal;

public enum DiscountStatus {
    BRONZE(BigDecimal.valueOf(5.5)),
    SILVER(BigDecimal.valueOf(10.5)),
    GOLD(BigDecimal.valueOf(15)),
    BRILLIANT(BigDecimal.valueOf(25)),
    PLATINUM(BigDecimal.valueOf(40));

    private final BigDecimal discount;

    DiscountStatus(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
