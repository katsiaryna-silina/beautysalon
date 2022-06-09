package by.silina.beautysalon.dto;

import by.silina.beautysalon.entity.DiscountStatus;
import by.silina.beautysalon.entity.Role;
import by.silina.beautysalon.entity.UserStatus;

import java.time.LocalDateTime;

public class UserAuthorizedDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Role role;
    private DiscountStatus discountStatus;
    private LocalDateTime lastLogin;
    private UserStatus userStatus;

    private UserAuthorizedDto() {
    }

    public static UserAuthorizedDto.UserAuthorizedDtoBuilder builder() {
        return new UserAuthorizedDto.UserAuthorizedDtoBuilder();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public DiscountStatus getDiscountStatus() {
        return discountStatus;
    }

    public void setDiscountStatus(DiscountStatus discountStatus) {
        this.discountStatus = discountStatus;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public static class UserAuthorizedDtoBuilder {
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private Role role;
        private DiscountStatus discountStatus;
        private LocalDateTime lastLogin;
        private UserStatus userStatus;

        UserAuthorizedDtoBuilder() {
        }

        public UserAuthorizedDto.UserAuthorizedDtoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserAuthorizedDto.UserAuthorizedDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserAuthorizedDto.UserAuthorizedDtoBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserAuthorizedDto.UserAuthorizedDtoBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserAuthorizedDto.UserAuthorizedDtoBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserAuthorizedDto.UserAuthorizedDtoBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserAuthorizedDto.UserAuthorizedDtoBuilder discountStatus(DiscountStatus discountStatus) {
            this.discountStatus = discountStatus;
            return this;
        }

        public UserAuthorizedDto.UserAuthorizedDtoBuilder lastLogin(LocalDateTime lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public UserAuthorizedDto.UserAuthorizedDtoBuilder userStatus(UserStatus userStatus) {
            this.userStatus = userStatus;
            return this;
        }

        public UserAuthorizedDto build() {
            UserAuthorizedDto userAuthorizedDto = new UserAuthorizedDto();
            userAuthorizedDto.role = this.role;
            userAuthorizedDto.email = this.email;
            userAuthorizedDto.firstName = this.firstName;
            userAuthorizedDto.lastName = this.lastName;
            userAuthorizedDto.lastLogin = this.lastLogin;
            userAuthorizedDto.username = this.username;
            userAuthorizedDto.phoneNumber = this.phoneNumber;
            userAuthorizedDto.discountStatus = this.discountStatus;
            userAuthorizedDto.userStatus = this.userStatus;
            return userAuthorizedDto;
        }
    }
}
