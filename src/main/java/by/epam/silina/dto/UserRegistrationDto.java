package by.epam.silina.dto;

public class UserRegistrationDto {
    private String username;
    private String password;
    private String repeatedPassword;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private UserRegistrationDto() {
    }

    public static UserRegistrationDto.UserRegistrationDtoBuilder builder() {
        return new UserRegistrationDto.UserRegistrationDtoBuilder();
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

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static class UserRegistrationDtoBuilder {
        private String username;
        private String password;
        private String repeatedPassword;
        private String email;
        private String firstName;
        private String lastName;
        private String phoneNumber;

        UserRegistrationDtoBuilder() {
        }

        public UserRegistrationDto.UserRegistrationDtoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserRegistrationDto.UserRegistrationDtoBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserRegistrationDto.UserRegistrationDtoBuilder repeatedPassword(String repeatedPassword) {
            this.repeatedPassword = repeatedPassword;
            return this;
        }

        public UserRegistrationDto.UserRegistrationDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserRegistrationDto.UserRegistrationDtoBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserRegistrationDto.UserRegistrationDtoBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserRegistrationDto.UserRegistrationDtoBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserRegistrationDto build() {
            UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
            userRegistrationDto.email = this.email;
            userRegistrationDto.password = this.password;
            userRegistrationDto.repeatedPassword = this.repeatedPassword;
            userRegistrationDto.firstName = this.firstName;
            userRegistrationDto.lastName = this.lastName;
            userRegistrationDto.username = this.username;
            userRegistrationDto.phoneNumber = this.phoneNumber;
            return userRegistrationDto;
        }
    }
}
