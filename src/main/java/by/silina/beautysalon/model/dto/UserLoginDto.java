package by.silina.beautysalon.model.dto;

/**
 * The UserLoginDto class.
 *
 * @author Silina Katsiaryna
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLoginDto that = (UserLoginDto) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("UserLoginDto{")
                .append("username='").append(username)
                .append(", password='").append(password)
                .append('}')
                .toString();
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
