package by.silina.beautysalon.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * The OrderForAdminDto class.
 *
 * @author Silina Katsiaryna
 */
public class OrderForAdminDto {
    private Long id;
    private LocalDateTime orderDateTime;
    private LocalDate visitDate;
    private LocalTime visitBeginTime;
    private LocalTime visitEndTime;
    private List<String> serviceNames;
    private BigDecimal priceWithDiscount;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String status;
    private String description;

    private OrderForAdminDto() {
    }

    public static OrderForAdminDtoBuilder builder() {
        return new OrderForAdminDtoBuilder();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public static class OrderForAdminDtoBuilder {
        private Long id;
        private LocalDateTime orderDateTime;
        private LocalDate visitDate;
        private LocalTime visitBeginTime;
        private LocalTime visitEndTime;
        private List<String> serviceNames;
        private BigDecimal priceWithDiscount;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String status;
        private String description;

        OrderForAdminDtoBuilder() {
        }

        public OrderForAdminDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderForAdminDtoBuilder orderDateTime(LocalDateTime orderDateTime) {
            this.orderDateTime = orderDateTime;
            return this;
        }

        public OrderForAdminDtoBuilder visitDate(LocalDate visitDate) {
            this.visitDate = visitDate;
            return this;
        }

        public OrderForAdminDtoBuilder visitBeginTime(LocalTime visitBeginTime) {
            this.visitBeginTime = visitBeginTime;
            return this;
        }

        public OrderForAdminDtoBuilder visitEndTime(LocalTime visitEndTime) {
            this.visitEndTime = visitEndTime;
            return this;
        }

        public OrderForAdminDtoBuilder serviceNames(List<String> serviceNames) {
            this.serviceNames = serviceNames;
            return this;
        }

        public OrderForAdminDtoBuilder priceWithDiscount(BigDecimal priceWithDiscount) {
            this.priceWithDiscount = priceWithDiscount;
            return this;
        }

        public OrderForAdminDtoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public OrderForAdminDtoBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public OrderForAdminDtoBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public OrderForAdminDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public OrderForAdminDtoBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public OrderForAdminDtoBuilder status(String status) {
            this.status = status;
            return this;
        }

        public OrderForAdminDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public OrderForAdminDto build() {
            OrderForAdminDto orderForAdminDto = new OrderForAdminDto();
            orderForAdminDto.id = this.id;
            orderForAdminDto.orderDateTime = this.orderDateTime;
            orderForAdminDto.visitDate = this.visitDate;
            orderForAdminDto.visitBeginTime = this.visitBeginTime;
            orderForAdminDto.visitEndTime = this.visitEndTime;
            orderForAdminDto.serviceNames = this.serviceNames;
            orderForAdminDto.priceWithDiscount = this.priceWithDiscount;
            orderForAdminDto.username = this.username;
            orderForAdminDto.firstName = this.firstName;
            orderForAdminDto.lastName = this.lastName;
            orderForAdminDto.email = this.email;
            orderForAdminDto.phoneNumber = this.phoneNumber;
            orderForAdminDto.status = this.status;
            orderForAdminDto.description = this.description;
            return orderForAdminDto;
        }
    }
}
