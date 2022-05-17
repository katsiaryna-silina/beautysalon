package by.epam.silina.entity;

public class UserStatus extends AbstractEntity {
    private String status;

    private UserStatus() {
    }

    public static UserStatus.UserStatusBuilder builder() {
        return new UserStatus.UserStatusBuilder();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserStatus that = (UserStatus) o;

        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "status='" + status + '\'' +
                '}';
    }

    public static class UserStatusBuilder {
        private Long id;
        private String status;

        UserStatusBuilder() {
        }

        public UserStatusBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserStatusBuilder status(String status) {
            this.status = status;
            return this;
        }

        public UserStatus build() {
            UserStatus userStatus = new UserStatus();
            userStatus.setId(id);
            userStatus.setStatus(status);
            return userStatus;
        }
    }
}
