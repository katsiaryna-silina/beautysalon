package by.epam.silina.entity;

public class DiscountStatus extends AbstractEntity {
    private String status;
    private Double discount;

    private DiscountStatus() {
    }

    public static DiscountStatus.DiscountStatusBuilder builder() {
        return new DiscountStatus.DiscountStatusBuilder();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
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
        return "DiscountStatus{" +
                "status='" + status + '\'' +
                ", discount=" + discount +
                '}';
    }

    public static final class DiscountStatusBuilder {
        private Long id;
        private String status;
        private Double discount;

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

        public DiscountStatusBuilder discount(Double discount) {
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
