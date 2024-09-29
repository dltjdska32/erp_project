package test.erp_project.dto.salary_dto;


import test.erp_project.dto.user_dto.UserRoleDto;

public class BonusDto {
    UserRoleDto usrRole;
    int totalSalary;
    Long userNum;


    public Long getUserNum() {
        return userNum;
    }
    public void setUserNum(Long userNum) {
        this.userNum = userNum;
    }


    public UserRoleDto getUsrRole() {
        return usrRole;
    }
    public void setUsrRole(UserRoleDto usrRole) {
        this.usrRole = usrRole;
    }
    public int getTotalSalary() {
        return totalSalary;
    }
    public void setTotalSalary(int totalSalary) {
        this.totalSalary = totalSalary;
    }



}
