package by.silina.beautysalon.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * The OrderForClientDto class.
 *
 * @author Silina Katsiaryna
 */
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderForClientDto that = (OrderForClientDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (orderDateTime != null ? !orderDateTime.equals(that.orderDateTime) : that.orderDateTime != null)
            return false;
        if (visitDate != null ? !visitDate.equals(that.visitDate) : that.visitDate != null) return false;
        if (visitBeginTime != null ? !visitBeginTime.equals(that.visitBeginTime) : that.visitBeginTime != null)
            return false;
        if (visitEndTime != null ? !visitEndTime.equals(that.visitEndTime) : that.visitEndTime != null) return false;
        if (serviceNames != null ? !serviceNames.equals(that.serviceNames) : that.serviceNames != null) return false;
        if (priceWithDiscount != null ? !priceWithDiscount.equals(that.priceWithDiscount) : that.priceWithDiscount != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderDateTime != null ? orderDateTime.hashCode() : 0);
        result = 31 * result + (visitDate != null ? visitDate.hashCode() : 0);
        result = 31 * result + (visitBeginTime != null ? visitBeginTime.hashCode() : 0);
        result = 31 * result + (visitEndTime != null ? visitEndTime.hashCode() : 0);
        result = 31 * result + (serviceNames != null ? serviceNames.hashCode() : 0);
        result = 31 * result + (priceWithDiscount != null ? priceWithDiscount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("OrderForClientDto{")
                .append("id=").append(id)
                .append("orderDateTime=").append(orderDateTime)
                .append(", visitDate=").append(visitDate)
                .append(", visitBeginTime=").append(visitBeginTime)
                .append(", visitEndTime=").append(visitEndTime)
                .append(", serviceNames=").append(serviceNames)
                .append(", priceWithDiscount=").append(priceWithDiscount)
                .append(", status=").append(status)
                .append(", status=").append(status)
                .append(", description=").append(description)
                .append('}')
                .toString();
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
