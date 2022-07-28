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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceListJsonDto that = (ServiceListJsonDto) o;

        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (totalNotFiltered != null ? !totalNotFiltered.equals(that.totalNotFiltered) : that.totalNotFiltered != null)
            return false;
        return rows != null ? rows.equals(that.rows) : that.rows == null;
    }

    @Override
    public int hashCode() {
        int result = total != null ? total.hashCode() : 0;
        result = 31 * result + (totalNotFiltered != null ? totalNotFiltered.hashCode() : 0);
        result = 31 * result + (rows != null ? rows.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("ServiceListJsonDto{")
                .append("total='").append(total)
                .append(", totalNotFiltered='").append(totalNotFiltered)
                .append(", rows='").append(rows)
                .append('}')
                .toString();
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
