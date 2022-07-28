package by.silina.beautysalon.model.dto;

/**
 * The UserPasswordsDto class.
 *
 * @author Silina Katsiaryna
 */
public class UserPasswordsDto {
    private Long userId;
    private String currentPassword;
    private String newPassword;
    private String repeatedNewPassword;

    private UserPasswordsDto() {
    }

    public static UserPasswordsDtoBuilder builder() {
        return new UserPasswordsDtoBuilder();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatedNewPassword() {
        return repeatedNewPassword;
    }

    public void setRepeatedNewPassword(String repeatedNewPassword) {
        this.repeatedNewPassword = repeatedNewPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPasswordsDto that = (UserPasswordsDto) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (currentPassword != null ? !currentPassword.equals(that.currentPassword) : that.currentPassword != null)
            return false;
        if (newPassword != null ? !newPassword.equals(that.newPassword) : that.newPassword != null) return false;
        return repeatedNewPassword != null ? repeatedNewPassword.equals(that.repeatedNewPassword) : that.repeatedNewPassword == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (currentPassword != null ? currentPassword.hashCode() : 0);
        result = 31 * result + (newPassword != null ? newPassword.hashCode() : 0);
        result = 31 * result + (repeatedNewPassword != null ? repeatedNewPassword.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("UserPasswordsDto{")
                .append("userId='").append(userId)
                .append(", currentPassword='").append(currentPassword)
                .append(", newPassword='").append(newPassword)
                .append(", repeatedNewPassword='").append(repeatedNewPassword)
                .append('}')
                .toString();
    }

    public static class UserPasswordsDtoBuilder {
        private Long userId;
        private String currentPassword;
        private String newPassword;
        private String repeatedNewPassword;

        UserPasswordsDtoBuilder() {
        }

        public UserPasswordsDtoBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserPasswordsDtoBuilder currentPassword(String currentPassword) {
            this.currentPassword = currentPassword;
            return this;
        }

        public UserPasswordsDtoBuilder newPassword(String newPassword) {
            this.newPassword = newPassword;
            return this;
        }

        public UserPasswordsDtoBuilder repeatedNewPassword(String repeatedNewPassword) {
            this.repeatedNewPassword = repeatedNewPassword;
            return this;
        }

        public UserPasswordsDto build() {
            UserPasswordsDto userPasswordsDto = new UserPasswordsDto();
            userPasswordsDto.userId = this.userId;
            userPasswordsDto.currentPassword = this.currentPassword;
            userPasswordsDto.newPassword = this.newPassword;
            userPasswordsDto.repeatedNewPassword = this.repeatedNewPassword;
            return userPasswordsDto;
        }
    }
}
