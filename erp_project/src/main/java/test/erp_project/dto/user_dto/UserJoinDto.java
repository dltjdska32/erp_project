package test.erp_project.dto.user_dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class UserJoinDto {

    @NotBlank(message = "아이디는 필수 정보입니다.")
    @Size(min = 5, max = 20, message = "아이디는 5자 이상, 20자 이하로 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 정보입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자이상, 20자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이름은 필수 정보입니다.")
    private String name;

    @NotBlank(message = "전화번호는 필수 정보입니다.")
    private String tel;

    @Email(message = "이메일 형식으로 입력해주세요.")
    @NotEmpty(message = "이메일은 필수 정보입니다.")
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
