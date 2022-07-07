package by.silina.beautysalon.model.dto;

import java.math.BigDecimal;

public class ServiceDto {
    Integer minutesNeeded;
    private Long id;
    private String name;
    private boolean isComplex;
    private String description;
    private BigDecimal price;
    private boolean isDeprecated;

    private ServiceDto() {
    }

    public static ServiceDtoBuilder builder() {
        return new ServiceDtoBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isDeprecated() {
        return isDeprecated;
    }

    public void setDeprecated(boolean deprecated) {
        isDeprecated = deprecated;
    }

    public Integer getMinutesNeeded() {
        return minutesNeeded;
    }

    public void setMinutesNeeded(Integer minutesNeeded) {
        this.minutesNeeded = minutesNeeded;
    }

    public static class ServiceDtoBuilder {
        private Long id;
        private String name;
        private boolean isComplex;
        private String description;
        private BigDecimal price;
        private boolean isDeprecated;
        private Integer minutesNeeded;

        ServiceDtoBuilder() {
        }

        public ServiceDtoBuilder id(Long id) {
            this.id = id;
            return this;
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

        public ServiceDtoBuilder isDeprecated(boolean isDeprecated) {
            this.isDeprecated = isDeprecated;
            return this;
        }

        public ServiceDtoBuilder minutesNeeded(Integer minutesNeeded) {
            this.minutesNeeded = minutesNeeded;
            return this;
        }

        public ServiceDto build() {
            ServiceDto serviceDto = new ServiceDto();
            serviceDto.setId(id);
            serviceDto.setName(name);
            serviceDto.setComplex(isComplex);
            serviceDto.setDescription(description);
            serviceDto.setPrice(price);
            serviceDto.setDeprecated(isDeprecated);
            serviceDto.setMinutesNeeded(minutesNeeded);
            return serviceDto;
        }
    }
}
