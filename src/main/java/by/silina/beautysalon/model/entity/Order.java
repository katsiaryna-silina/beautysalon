package by.silina.beautysalon.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Order extends AbstractEntity {
    private LocalDateTime orderDateTime;
    private LocalDate visitDate;
    private LocalTime visitBeginTime;
    private LocalTime visitEndTime;
    private User user;
    private List<Serv> services;
    private List<VisitTime> timeSlots;
    private OrderStatus orderStatus;
    private BigDecimal priceWithDiscount;
    private OrderFeedback orderFeedback;

    private Order() {
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public LocalTime getVisitBeginTime() {
        return visitBeginTime;
    }

    public void setVisitBeginTime(LocalTime visitBeginTime) {
        this.visitBeginTime = visitBeginTime;
    }

    public LocalTime getVisitEndTime() {
        return visitEndTime;
    }

    public void setVisitEndTime(LocalTime visitEndTime) {
        this.visitEndTime = visitEndTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Serv> getServices() {
        return services;
    }

    public void setServices(List<Serv> services) {
        this.services = services;
    }

    public List<VisitTime> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<VisitTime> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public OrderFeedback getOrderFeedback() {
        return orderFeedback;
    }

    public void setOrderFeedback(OrderFeedback orderFeedback) {
        this.orderFeedback = orderFeedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (orderDateTime != null ? !orderDateTime.equals(order.orderDateTime) : order.orderDateTime != null)
            return false;
        if (visitDate != null ? !visitDate.equals(order.visitDate) : order.visitDate != null) return false;
        if (visitBeginTime != null ? !visitBeginTime.equals(order.visitBeginTime) : order.visitBeginTime != null)
            return false;
        if (visitEndTime != null ? !visitEndTime.equals(order.visitEndTime) : order.visitEndTime != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (services != null ? !services.equals(order.services) : order.services != null) return false;
        if (timeSlots != null ? !timeSlots.equals(order.timeSlots) : order.timeSlots != null) return false;
        if (orderStatus != null ? !orderStatus.equals(order.orderStatus) : order.orderStatus != null) return false;
        if (priceWithDiscount != null ? !priceWithDiscount.equals(order.priceWithDiscount) : order.priceWithDiscount != null)
            return false;
        return orderFeedback != null ? orderFeedback.equals(order.orderFeedback) : order.orderFeedback == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (orderDateTime != null ? orderDateTime.hashCode() : 0);
        result = 31 * result + (visitDate != null ? visitDate.hashCode() : 0);
        result = 31 * result + (visitBeginTime != null ? visitBeginTime.hashCode() : 0);
        result = 31 * result + (visitEndTime != null ? visitEndTime.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (services != null ? services.hashCode() : 0);
        result = 31 * result + (timeSlots != null ? timeSlots.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (priceWithDiscount != null ? priceWithDiscount.hashCode() : 0);
        result = 31 * result + (orderFeedback != null ? orderFeedback.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Order{")
                .append("orderDateTime=").append(orderDateTime)
                .append(", visitDate=").append(visitDate)
                .append(", visitBeginTime=").append(visitBeginTime)
                .append(", visitEndTime=").append(visitEndTime)
                .append(", user=").append(user)
                .append(", services=").append(services)
                .append(", timeSlots=").append(timeSlots)
                .append(", orderStatus=").append(orderStatus)
                .append(", priceWithDiscount=").append(priceWithDiscount)
                .append(", orderFeedback=").append(orderFeedback)
                .append('}')
                .toString();
    }

    public static class OrderBuilder {
        private Long id;
        private LocalDateTime orderDateTime;
        private LocalDate visitDate;
        private LocalTime visitBeginTime;
        private LocalTime visitEndTime;
        private User user;
        private List<Serv> services;
        private List<VisitTime> timeSlots;
        private OrderStatus orderStatus;
        private BigDecimal priceWithDiscount;
        private OrderFeedback orderFeedback;

        OrderBuilder() {
        }

        public OrderBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder orderDateTime(LocalDateTime orderDateTime) {
            this.orderDateTime = orderDateTime;
            return this;
        }

        public OrderBuilder visitDate(LocalDate visitDate) {
            this.visitDate = visitDate;
            return this;
        }

        public OrderBuilder visitBeginTime(LocalTime visitBeginTime) {
            this.visitBeginTime = visitBeginTime;
            return this;
        }

        public OrderBuilder visitEndTime(LocalTime visitEndTime) {
            this.visitEndTime = visitEndTime;
            return this;
        }

        public OrderBuilder user(User user) {
            this.user = user;
            return this;
        }

        public OrderBuilder services(List<Serv> services) {
            this.services = services;
            return this;
        }

        public OrderBuilder timeSlots(List<VisitTime> timeSlots) {
            this.timeSlots = timeSlots;
            return this;
        }

        public OrderBuilder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderBuilder priceWithDiscount(BigDecimal priceWithDiscount) {
            this.priceWithDiscount = priceWithDiscount;
            return this;
        }

        public OrderBuilder orderFeedback(OrderFeedback orderFeedback) {
            this.orderFeedback = orderFeedback;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setId(id);
            order.setOrderDateTime(orderDateTime);
            order.setVisitDate(visitDate);
            order.setVisitBeginTime(visitBeginTime);
            order.setVisitEndTime(visitEndTime);
            order.setUser(user);
            order.setServices(services);
            order.setTimeSlots(timeSlots);
            order.setOrderStatus(orderStatus);
            order.setPriceWithDiscount(priceWithDiscount);
            order.setOrderFeedback(orderFeedback);
            return order;
        }
    }
}
