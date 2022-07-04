package by.silina.beautysalon.model.dto;

import java.math.BigDecimal;

public class ServiceDto {
    private String name;
    private boolean isComplex;
    private String description;
    private BigDecimal price;

    private ServiceDto() {
    }

    public static ServiceDtoBuilder builder() {
        return new ServiceDtoBuilder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplex() {
        return isComplex;
    }

    public void setComplex(boolean complex) {
        isComplex = complex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static class ServiceDtoBuilder {
        private String name;
        private boolean isComplex;
        private String description;
        private BigDecimal price;

        ServiceDtoBuilder() {
        }

        public ServiceDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ServiceDtoBuilder isComplex(boolean isComplex) {
            this.isComplex = isComplex;
            return this;
        }

        public ServiceDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ServiceDtoBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ServiceDto build() {
            ServiceDto serviceDto = new ServiceDto();
            serviceDto.setName(name);
            serviceDto.setComplex(isComplex);
            serviceDto.setDescription(description);
            serviceDto.setPrice(price);
            return serviceDto;
        }
    }
}
