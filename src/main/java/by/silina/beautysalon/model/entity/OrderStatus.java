package by.silina.beautysalon.model.entity;

/**
 * The OrderStatus class.
 *
 * @author Silina Katsiaryna
 */
public class OrderStatus extends AbstractEntity {
    private String status;
    private String description;
    private Role accessibleTo;

    private OrderStatus() {
    }

    public static OrderStatusBuilder builder() {
        return new OrderStatusBuilder();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getAccessibleTo() {
        return accessibleTo;
    }

    public void setAccessibleTo(Role accessibleTo) {
        this.accessibleTo = accessibleTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderStatus that = (OrderStatus) o;

        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return accessibleTo == that.accessibleTo;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (accessibleTo != null ? accessibleTo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("OrderStatus{")
                .append("status='").append(status)
                .append(", description='").append(description)
                .append(", accessibleTo='").append(accessibleTo)
                .append('}')
                .toString();
    }

    public static class OrderStatusBuilder {
        private Long id;
        private String status;
        private String description;
        private Role accessibleTo;

        OrderStatusBuilder() {
        }

        public OrderStatusBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderStatusBuilder status(String status) {
            this.status = status;
            return this;
        }

        public OrderStatusBuilder description(String description) {
            this.description = description;
            return this;
        }

        public OrderStatusBuilder accessibleTo(Role accessibleTo) {
            this.accessibleTo = accessibleTo;
            return this;
        }

        public OrderStatus build() {
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setId(id);
            orderStatus.setStatus(status);
            orderStatus.setDescription(description);
            orderStatus.setAccessibleTo(accessibleTo);
            return orderStatus;
        }
    }
}
