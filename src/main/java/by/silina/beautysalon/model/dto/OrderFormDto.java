package by.silina.beautysalon.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * The OrderFormDto class.
 *
 * @author Silina Katsiaryna
 */
public class OrderFormDto {
    private LocalDate visitDate;
    private Long userId;
    private LocalTime visitBeginTime;
    private LocalTime visitEndTime;
    private List<Long> timeSlotIds;
    private BigDecimal priceWithDiscount;
    private List<Long> servicesIds;

    private OrderFormDto() {
    }

    public static OrderFormDtoBuilder builder() {
        return new OrderFormDtoBuilder();
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getTimeSlotIds() {
        return timeSlotIds;
    }

    public void setTimeSlotIds(List<Long> timeSlotIds) {
        this.timeSlotIds = timeSlotIds;
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

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public List<Long> getServicesIds() {
        return servicesIds;
    }

    public void setServicesIds(List<Long> servicesIds) {
        this.servicesIds = servicesIds;
    }

    public static class OrderFormDtoBuilder {
        private LocalDate visitDate;
        private Long userId;
        private List<Long> timeSlotIds;
        private LocalTime visitBeginTime;
        private LocalTime visitEndTime;
        private BigDecimal priceWithDiscount;
        private List<Long> servicesIds;

        OrderFormDtoBuilder() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OrderFormDtoBuilder that = (OrderFormDtoBuilder) o;

            if (visitDate != null ? !visitDate.equals(that.visitDate) : that.visitDate != null) return false;
            if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
            if (timeSlotIds != null ? !timeSlotIds.equals(that.timeSlotIds) : that.timeSlotIds != null) return false;
            if (visitBeginTime != null ? !visitBeginTime.equals(that.visitBeginTime) : that.visitBeginTime != null)
                return false;
            if (visitEndTime != null ? !visitEndTime.equals(that.visitEndTime) : that.visitEndTime != null)
                return false;
            if (priceWithDiscount != null ? !priceWithDiscount.equals(that.priceWithDiscount) : that.priceWithDiscount != null)
                return false;
            return servicesIds != null ? servicesIds.equals(that.servicesIds) : that.servicesIds == null;
        }

        @Override
        public int hashCode() {
            int result = visitDate != null ? visitDate.hashCode() : 0;
            result = 31 * result + (userId != null ? userId.hashCode() : 0);
            result = 31 * result + (timeSlotIds != null ? timeSlotIds.hashCode() : 0);
            result = 31 * result + (visitBeginTime != null ? visitBeginTime.hashCode() : 0);
            result = 31 * result + (visitEndTime != null ? visitEndTime.hashCode() : 0);
            result = 31 * result + (priceWithDiscount != null ? priceWithDiscount.hashCode() : 0);
            result = 31 * result + (servicesIds != null ? servicesIds.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("OrderFormDtoBuilder{")
                    .append(", visitDate=").append(visitDate)
                    .append(", userId=").append(userId)
                    .append(", timeSlotIds=").append(timeSlotIds)
                    .append(", visitBeginTime=").append(visitBeginTime)
                    .append(", visitEndTime=").append(visitEndTime)
                    .append(", priceWithDiscount=").append(priceWithDiscount)
                    .append(", servicesIds=").append(servicesIds)
                    .append('}')
                    .toString();
        }

        public OrderFormDtoBuilder visitDate(LocalDate visitDate) {
            this.visitDate = visitDate;
            return this;
        }

        public OrderFormDtoBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public OrderFormDtoBuilder timeSlotIds(List<Long> timeSlotIds) {
            this.timeSlotIds = timeSlotIds;
            return this;
        }

        public OrderFormDtoBuilder visitBeginTime(LocalTime visitBeginTime) {
            this.visitBeginTime = visitBeginTime;
            return this;
        }

        public OrderFormDtoBuilder visitEndTime(LocalTime visitEndTime) {
            this.visitEndTime = visitEndTime;
            return this;
        }

        public OrderFormDtoBuilder priceWithDiscount(BigDecimal priceWithDiscount) {
            this.priceWithDiscount = priceWithDiscount;
            return this;
        }

        public OrderFormDtoBuilder servicesIds(List<Long> servicesIds) {
            this.servicesIds = servicesIds;
            return this;
        }

        public OrderFormDto build() {
            OrderFormDto orderFormDto = new OrderFormDto();
            orderFormDto.setVisitDate(visitDate);
            orderFormDto.setUserId(userId);
            orderFormDto.setTimeSlotIds(timeSlotIds);
            orderFormDto.setVisitBeginTime(visitBeginTime);
            orderFormDto.setVisitEndTime(visitEndTime);
            orderFormDto.setPriceWithDiscount(priceWithDiscount);
            orderFormDto.setServicesIds(servicesIds);
            return orderFormDto;
        }
    }
}
