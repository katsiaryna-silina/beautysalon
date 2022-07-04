package by.silina.beautysalon.model.dto;

import java.util.List;

public class UserListJsonDto {
    private Long total;
    private Long totalNotFiltered;
    private List<UserAuthorizedDto> rows;

    private UserListJsonDto() {
    }

    public static UserJsonDtoBuilder builder() {
        return new UserJsonDtoBuilder();
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

    public List<UserAuthorizedDto> getRows() {
        return rows;
    }

    public void setRows(List<UserAuthorizedDto> rows) {
        this.rows = rows;
    }

    public static class UserJsonDtoBuilder {
        private Long total;
        private Long totalNotFiltered;
        private List<UserAuthorizedDto> data;

        UserJsonDtoBuilder() {
        }

        public UserListJsonDto.UserJsonDtoBuilder recordsTotal(Long recordsTotal) {
            this.total = recordsTotal;
            return this;
        }

        public UserListJsonDto.UserJsonDtoBuilder recordsFiltered(Long recordsFiltered) {
            this.totalNotFiltered = recordsFiltered;
            return this;
        }

        public UserListJsonDto.UserJsonDtoBuilder rows(List<UserAuthorizedDto> data) {
            this.data = data;
            return this;
        }

        public UserListJsonDto build() {
            UserListJsonDto userJsonDto = new UserListJsonDto();
            userJsonDto.total = this.total;
            userJsonDto.totalNotFiltered = this.totalNotFiltered;
            userJsonDto.rows = this.data;
            return userJsonDto;
        }
    }
}
