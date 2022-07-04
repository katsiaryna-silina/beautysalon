package by.silina.beautysalon.model.dto;

public class UserLoginDto {
    private String username;
    private String password;

    private UserLoginDto() {
    }

    public static UserLoginDtoBuilder builder() {
        return new UserLoginDtoBuilder();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class UserLoginDtoBuilder {
        private String username;
        private String password;

        UserLoginDtoBuilder() {
        }

        public UserLoginDto.UserLoginDtoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserLoginDto.UserLoginDtoBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserLoginDto build() {
            UserLoginDto userLoginDto = new UserLoginDto();
            userLoginDto.username = this.username;
            userLoginDto.password = this.password;
            return userLoginDto;
        }
    }
}
