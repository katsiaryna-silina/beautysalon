package by.silina.beautysalon.model.dto;

import by.silina.beautysalon.model.entity.DiscountStatus;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.model.entity.UserStatus;

import java.time.LocalDateTime;

/**
 * The UserAuthorizedDto class.
 *
 * @author Silina Katsiaryna
 */
public class UserAuthorizedDto {
    private Long id;
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

    public static UserAuthorizedDtoBuilder builder() {
        return new UserAuthorizedDtoBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuthorizedDto that = (UserAuthorizedDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (role != that.role) return false;
        if (discountStatus != null ? !discountStatus.equals(that.discountStatus) : that.discountStatus != null)
            return false;
        if (lastLogin != null ? !lastLogin.equals(that.lastLogin) : that.lastLogin != null) return false;
        return userStatus == that.userStatus;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (discountStatus != null ? discountStatus.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        result = 31 * result + (userStatus != null ? userStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("UserAuthorizedDto{")
                .append(", id='").append(id).append('\'')
                .append("username='").append(username).append('\'')
                .append(", email='").append(email).append('\'')
                .append(", firstName='").append(firstName).append('\'')
                .append(", lastName='").append(lastName).append('\'')
                .append(", phoneNumber='").append(phoneNumber).append('\'')
                .append(", role=").append(role).append('\'')
                .append(", discountStatus=").append(discountStatus).append('\'')
                .append(", lastLogin=").append(lastLogin).append('\'')
                .append(", userStatus=").append(userStatus)
                .append('}')
                .toString();
    }

    public static class UserAuthorizedDtoBuilder {
        private Long id;
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

        public UserAuthorizedDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserAuthorizedDtoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserAuthorizedDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserAuthorizedDtoBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserAuthorizedDtoBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserAuthorizedDtoBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserAuthorizedDtoBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserAuthorizedDtoBuilder discountStatus(DiscountStatus discountStatus) {
            this.discountStatus = discountStatus;
            return this;
        }

        public UserAuthorizedDtoBuilder lastLogin(LocalDateTime lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public UserAuthorizedDtoBuilder userStatus(UserStatus userStatus) {
            this.userStatus = userStatus;
            return this;
        }

        public UserAuthorizedDto build() {
            UserAuthorizedDto userAuthorizedDto = new UserAuthorizedDto();
            userAuthorizedDto.id = this.id;
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
