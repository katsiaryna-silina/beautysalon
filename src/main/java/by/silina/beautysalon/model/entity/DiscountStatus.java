package by.silina.beautysalon.model.entity;

import java.math.BigDecimal;

/**
 * The DiscountStatus class.
 *
 * @author Silina Katsiaryna
 */
public class DiscountStatus extends AbstractEntity {
    private String status;
    private BigDecimal discount;

    private DiscountStatus() {
    }

    public static DiscountStatusBuilder builder() {
        return new DiscountStatusBuilder();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DiscountStatus that = (DiscountStatus) o;

        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return discount != null ? discount.equals(that.discount) : that.discount == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("DiscountStatus{")
                .append("status='").append(status)
                .append(", discount='").append(discount)
                .append('}')
                .toString();
    }

    public static class DiscountStatusBuilder {
        private Long id;
        private String status;
        private BigDecimal discount;

        DiscountStatusBuilder() {
        }

        public DiscountStatusBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DiscountStatusBuilder status(String status) {
            this.status = status;
            return this;
        }

        public DiscountStatusBuilder discount(BigDecimal discount) {
            this.discount = discount;
            return this;
        }

        public DiscountStatus build() {
            DiscountStatus discountStatus = new DiscountStatus();
            discountStatus.setId(id);
            discountStatus.setStatus(status);
            discountStatus.setDiscount(discount);
            return discountStatus;
        }
    }
}
