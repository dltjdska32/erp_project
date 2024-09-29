package test.erp_project.dto.user_dto;


import test.erp_project.domain.user.Role;

public class UserIdAndRole {
    private String userId;

    private Role role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
