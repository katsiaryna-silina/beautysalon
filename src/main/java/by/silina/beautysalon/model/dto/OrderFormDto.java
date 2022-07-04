package by.silina.beautysalon.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
