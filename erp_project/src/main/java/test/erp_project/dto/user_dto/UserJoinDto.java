package test.erp_project.dto.user_dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class UserJoinDto {

    @NotBlank(message = "아이디를 입력해주세요. ")
    private String userId;

    @NotBlank(message = "패스워드를 입력해주세요.")
    @Range(min = 8, max = 25, message = "8 ~ 25자리 사이의 패스워드를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String tel;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
