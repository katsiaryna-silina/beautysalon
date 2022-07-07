package by.silina.beautysalon.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class OrderForClientDto {
    private Long id;
    private LocalDateTime orderDateTime;
    private LocalDate visitDate;
    private LocalTime visitBeginTime;
    private LocalTime visitEndTime;
    private List<String> serviceNames;
    private BigDecimal priceWithDiscount;
    private String status;
    private String description;

    private OrderForClientDto() {
    }

    public static OrderForClientDtoBuilder builder() {
        return new OrderForClientDtoBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getServiceNames() {
        return serviceNames;
    }

    public void setServiceNames(List<String> serviceNames) {
        this.serviceNames = serviceNames;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
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

    public static class OrderForClientDtoBuilder {
        private Long id;
        private LocalDateTime orderDateTime;
        private LocalDate visitDate;
        private LocalTime visitBeginTime;
        private LocalTime visitEndTime;
        private List<String> serviceNames;
        private BigDecimal priceWithDiscount;
        private String status;
        private String description;

        OrderForClientDtoBuilder() {
        }

        public OrderForClientDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderForClientDtoBuilder orderDateTime(LocalDateTime orderDateTime) {
            this.orderDateTime = orderDateTime;
            return this;
        }

        public OrderForClientDtoBuilder visitDate(LocalDate visitDate) {
            this.visitDate = visitDate;
            return this;
        }

        public OrderForClientDtoBuilder visitBeginTime(LocalTime visitBeginTime) {
            this.visitBeginTime = visitBeginTime;
            return this;
        }

        public OrderForClientDtoBuilder visitEndTime(LocalTime visitEndTime) {
            this.visitEndTime = visitEndTime;
            return this;
        }

        public OrderForClientDtoBuilder serviceNames(List<String> serviceNames) {
            this.serviceNames = serviceNames;
            return this;
        }

        public OrderForClientDtoBuilder priceWithDiscount(BigDecimal priceWithDiscount) {
            this.priceWithDiscount = priceWithDiscount;
            return this;
        }

        public OrderForClientDtoBuilder status(String status) {
            this.status = status;
            return this;
        }

        public OrderForClientDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public OrderForClientDto build() {
            OrderForClientDto orderForClientDto = new OrderForClientDto();
            orderForClientDto.id = this.id;
            orderForClientDto.orderDateTime = this.orderDateTime;
            orderForClientDto.visitDate = this.visitDate;
            orderForClientDto.visitBeginTime = this.visitBeginTime;
            orderForClientDto.visitEndTime = this.visitEndTime;
            orderForClientDto.serviceNames = this.serviceNames;
            orderForClientDto.priceWithDiscount = this.priceWithDiscount;
            orderForClientDto.status = this.status;
            orderForClientDto.description = this.description;
            return orderForClientDto;
        }
    }
}
