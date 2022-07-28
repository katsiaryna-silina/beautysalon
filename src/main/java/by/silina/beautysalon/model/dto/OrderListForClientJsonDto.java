package by.silina.beautysalon.model.dto;

import java.util.List;

/**
 * The OrderListForClientJsonDto class.
 *
 * @author Silina Katsiaryna
 */
public class OrderListForClientJsonDto {
    private Long total;
    private Long totalNotFiltered;
    private List<OrderForClientDto> rows;

    private OrderListForClientJsonDto() {
    }

    public static OrderListForClientJsonDtoBuilder builder() {
        return new OrderListForClientJsonDtoBuilder();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotalNotFiltered() {
        return totalNotFiltered;
    }

    public void setTotalNotFiltered(Long totalNotFiltered) {
        this.totalNotFiltered = totalNotFiltered;
    }

    public List<OrderForClientDto> getRows() {
        return rows;
    }

    public void setRows(List<OrderForClientDto> rows) {
        this.rows = rows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderListForClientJsonDto that = (OrderListForClientJsonDto) o;

        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (totalNotFiltered != null ? !totalNotFiltered.equals(that.totalNotFiltered) : that.totalNotFiltered != null)
            return false;
        return rows != null ? rows.equals(that.rows) : that.rows == null;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("OrderListForClientJsonDto{")
                .append("total=").append(total)
                .append(", totalNotFiltered=").append(totalNotFiltered)
                .append(", rows=").append(rows)
                .append('}')
                .toString();
    }

    @Override
    public int hashCode() {
        int result = total != null ? total.hashCode() : 0;
        result = 31 * result + (totalNotFiltered != null ? totalNotFiltered.hashCode() : 0);
        result = 31 * result + (rows != null ? rows.hashCode() : 0);
        return result;
    }

    public static class OrderListForClientJsonDtoBuilder {
        private Long total;
        private Long totalNotFiltered;
        private List<OrderForClientDto> data;

        OrderListForClientJsonDtoBuilder() {
        }

        public OrderListForClientJsonDtoBuilder recordsTotal(Long recordsTotal) {
            this.total = recordsTotal;
            return this;
        }

        public OrderListForClientJsonDtoBuilder recordsFiltered(Long recordsFiltered) {
            this.totalNotFiltered = recordsFiltered;
            return this;
        }

        public OrderListForClientJsonDtoBuilder rows(List<OrderForClientDto> data) {
            this.data = data;
            return this;
        }

        public OrderListForClientJsonDto build() {
            OrderListForClientJsonDto jsonDto = new OrderListForClientJsonDto();
            jsonDto.total = this.total;
            jsonDto.totalNotFiltered = this.totalNotFiltered;
            jsonDto.rows = this.data;
            return jsonDto;
        }
    }
}
