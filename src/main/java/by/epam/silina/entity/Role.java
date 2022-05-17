package by.epam.silina.entity;

public class Role extends AbstractEntity {
    private String role;

    private Role() {
    }

    public static Role.RoleBuilder builder() {
        return new Role.RoleBuilder();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Role role1 = (Role) o;

        return role != null ? role.equals(role1.role) : role1.role == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                '}';
    }

    public static class RoleBuilder {
        private Long id;
        private String role;

        RoleBuilder() {
        }

        public RoleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RoleBuilder role(String role) {
            this.role = role;
            return this;
        }

        public Role build() {
            Role userRole = new Role();
            userRole.setId(id);
            userRole.role = this.role;
            return userRole;
        }
    }
}
