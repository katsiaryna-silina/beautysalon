package by.silina.beautysalon.model.dto;

import by.silina.beautysalon.model.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class DisplayingOrderDto {
    private Long id;
    private LocalDateTime orderDateTime;
    private String userFirstName;
    private String userLastName;
    private String email;
    private String phoneNumber;
    private LocalDateTime visitDateTime;
    private List<String> serviceNames;
    private BigDecimal priceWithDiscount;
    private OrderStatus orderStatus;
    private String declineReason;

    private DisplayingOrderDto() {
    }

    public static DisplayingOrderDtoBuilder builder() {
        return new DisplayingOrderDtoBuilder();
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

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getVisitDateTime() {
        return visitDateTime;
    }

    public void setVisitDateTime(LocalDateTime visitDateTime) {
        this.visitDateTime = visitDateTime;
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeclineReason() {
        return declineReason;
    }

    public void setDeclineReason(String declineReason) {
        this.declineReason = declineReason;
    }

    public static class DisplayingOrderDtoBuilder {
        private Long id;
        private LocalDateTime orderDateTime;
        private String userFirstName;
        private String userLastName;
        private String email;
        private String phoneNumber;
        private LocalDateTime visitDateTime;
        private List<String> serviceNames;
        private BigDecimal priceWithDiscount;
        private OrderStatus orderStatus;
        private String declineReason;

        DisplayingOrderDtoBuilder() {
        }

        public DisplayingOrderDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DisplayingOrderDtoBuilder userFirstName(String userFirstName) {
            this.userFirstName = userFirstName;
            return this;
        }

        public DisplayingOrderDtoBuilder userLastName(String userLastName) {
            this.userLastName = userLastName;
            return this;
        }

        public DisplayingOrderDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public DisplayingOrderDtoBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public DisplayingOrderDtoBuilder visitDateTime(LocalDateTime visitDateTime) {
            this.visitDateTime = visitDateTime;
            return this;
        }

        public DisplayingOrderDtoBuilder serviceNames(List<String> serviceNames) {
            this.serviceNames = serviceNames;
            return this;
        }

        public DisplayingOrderDtoBuilder priceWithDiscount(BigDecimal priceWithDiscount) {
            this.priceWithDiscount = priceWithDiscount;
            return this;
        }

        public DisplayingOrderDtoBuilder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public DisplayingOrderDtoBuilder declineReason(String declineReason) {
            this.declineReason = declineReason;
            return this;
        }

        public DisplayingOrderDto build() {
            DisplayingOrderDto displayingOrderDto = new DisplayingOrderDto();
            displayingOrderDto.id = this.id;
            displayingOrderDto.orderDateTime = this.orderDateTime;
            displayingOrderDto.userFirstName = this.userFirstName;
            displayingOrderDto.userLastName = this.userLastName;
            displayingOrderDto.email = this.email;
            displayingOrderDto.phoneNumber = this.phoneNumber;
            displayingOrderDto.visitDateTime = this.visitDateTime;
            displayingOrderDto.serviceNames = this.serviceNames;
            displayingOrderDto.priceWithDiscount = this.priceWithDiscount;
            displayingOrderDto.orderStatus = this.orderStatus;
            displayingOrderDto.declineReason = this.declineReason;
            return displayingOrderDto;
        }
    }
}
