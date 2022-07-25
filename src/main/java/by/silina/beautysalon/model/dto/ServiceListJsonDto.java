package by.silina.beautysalon.model.dto;

import java.util.List;

/**
 * The ServiceListJsonDto class.
 *
 * @author Silina Katsiaryna
 */
public class ServiceListJsonDto {
    private Long total;
    private Long totalNotFiltered;
    private List<ServiceDto> rows;

    private ServiceListJsonDto() {
    }

    public static ServiceListJsonDtoBuilder builder() {
        return new ServiceListJsonDtoBuilder();
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

    public List<ServiceDto> getRows() {
        return rows;
    }

    public void setRows(List<ServiceDto> rows) {
        this.rows = rows;
    }

    public static class ServiceListJsonDtoBuilder {
        private Long total;
        private Long totalNotFiltered;
        private List<ServiceDto> data;

        ServiceListJsonDtoBuilder() {
        }

        public ServiceListJsonDtoBuilder recordsTotal(Long recordsTotal) {
            this.total = recordsTotal;
            return this;
        }

        public ServiceListJsonDtoBuilder recordsFiltered(Long recordsFiltered) {
            this.totalNotFiltered = recordsFiltered;
            return this;
        }

        public ServiceListJsonDtoBuilder rows(List<ServiceDto> data) {
            this.data = data;
            return this;
        }

        public ServiceListJsonDto build() {
            ServiceListJsonDto jsonDto = new ServiceListJsonDto();
            jsonDto.total = this.total;
            jsonDto.totalNotFiltered = this.totalNotFiltered;
            jsonDto.rows = this.data;
            return jsonDto;
        }
    }
}
