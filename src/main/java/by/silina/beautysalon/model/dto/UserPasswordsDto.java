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
