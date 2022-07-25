package by.silina.beautysalon.model.entity;

import java.math.BigDecimal;

/**
 * The Serv class.
 *
 * @author Silina Katsiaryna
 */
public class Serv extends AbstractEntity {
    private String name;
    private boolean isComplex;
    private Integer minutesNeeded;
    private String description;
    private BigDecimal price;
    private boolean isDeprecated;

    private Serv() {
    }

    public static ServBuilder builder() {
        return new ServBuilder();
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

    public Integer getMinutesNeeded() {
        return minutesNeeded;
    }

    public void setMinutesNeeded(Integer minutesNeeded) {
        this.minutesNeeded = minutesNeeded;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Serv serv = (Serv) o;

        if (isComplex != serv.isComplex) return false;
        if (isDeprecated != serv.isDeprecated) return false;
        if (name != null ? !name.equals(serv.name) : serv.name != null) return false;
        if (minutesNeeded != null ? !minutesNeeded.equals(serv.minutesNeeded) : serv.minutesNeeded != null)
            return false;
        if (description != null ? !description.equals(serv.description) : serv.description != null) return false;
        return price != null ? price.equals(serv.price) : serv.price == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isComplex ? 1 : 0);
        result = 31 * result + (minutesNeeded != null ? minutesNeeded.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (isDeprecated ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Serv{")
                .append("name='").append(name)
                .append(", isComplex='").append(isComplex)
                .append(", minutesNeeded='").append(minutesNeeded)
                .append(", description='").append(description)
                .append(", price=").append(price)
                .append(", isDeprecated=").append(isDeprecated)
                .append('}')
                .toString();
    }

    public static class ServBuilder {
        private Long id;
        private String name;
        private boolean isComplex;
        private Integer minutesNeeded;
        private String description;
        private BigDecimal price;
        private boolean isDeprecated;

        ServBuilder() {
        }

        public ServBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ServBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ServBuilder isComplex(boolean isComplex) {
            this.isComplex = isComplex;
            return this;
        }

        public ServBuilder minutesNeeded(Integer minutesNeeded) {
            this.minutesNeeded = minutesNeeded;
            return this;
        }

        public ServBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ServBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ServBuilder isDeprecated(boolean isDeprecated) {
            this.isDeprecated = isDeprecated;
            return this;
        }

        public Serv build() {
            Serv serv = new Serv();
            serv.setId(id);
            serv.setName(name);
            serv.setComplex(isComplex);
            serv.setMinutesNeeded(minutesNeeded);
            serv.setDescription(description);
            serv.setPrice(price);
            serv.setDeprecated(isDeprecated);
            return serv;
        }
    }
}
