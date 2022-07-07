package by.silina.beautysalon.model.dto;

import java.util.List;

public class OrderListForAdminJsonDto {
    private Long total;
    private Long totalNotFiltered;
    private List<OrderForAdminDto> rows;

    private OrderListForAdminJsonDto() {
    }

    public static OrderListForAdminJsonDtoBuilder builder() {
        return new OrderListForAdminJsonDtoBuilder();
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

    public List<OrderForAdminDto> getRows() {
        return rows;
    }

    public void setRows(List<OrderForAdminDto> rows) {
        this.rows = rows;
    }

    public static class OrderListForAdminJsonDtoBuilder {
        private Long total;
        private Long totalNotFiltered;
        private List<OrderForAdminDto> data;

        OrderListForAdminJsonDtoBuilder() {
        }

        public OrderListForAdminJsonDtoBuilder recordsTotal(Long recordsTotal) {
            this.total = recordsTotal;
            return this;
        }

        public OrderListForAdminJsonDtoBuilder recordsFiltered(Long recordsFiltered) {
            this.totalNotFiltered = recordsFiltered;
            return this;
        }

        public OrderListForAdminJsonDtoBuilder rows(List<OrderForAdminDto> data) {
            this.data = data;
            return this;
        }

        public OrderListForAdminJsonDto build() {
            OrderListForAdminJsonDto jsonDto = new OrderListForAdminJsonDto();
            jsonDto.total = this.total;
            jsonDto.totalNotFiltered = this.totalNotFiltered;
            jsonDto.rows = this.data;
            return jsonDto;
        }
    }
}
