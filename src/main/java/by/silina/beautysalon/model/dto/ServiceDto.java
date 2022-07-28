package by.silina.beautysalon.model.dto;

import java.math.BigDecimal;

/**
 * The ServiceDto class.
 *
 * @author Silina Katsiaryna
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceDto that = (ServiceDto) o;

        if (isComplex != that.isComplex) return false;
        if (isDeprecated != that.isDeprecated) return false;
        if (minutesNeeded != null ? !minutesNeeded.equals(that.minutesNeeded) : that.minutesNeeded != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = minutesNeeded != null ? minutesNeeded.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isComplex ? 1 : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (isDeprecated ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(", minutesNeeded='").append(minutesNeeded)
                .append(", id='").append(id)
                .append(", isComplex='").append(isComplex)
                .append(", description='").append(description)
                .append(", price=").append(price)
                .append(", isDeprecated=").append(isDeprecated)
                .append('}')
                .toString();
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
