package test.erp_project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class LoginController {

    @RequestMapping("/")
    public String login() {
        log.info("Login controller");
        System.out.println("로그인유저");
        return "login-user";
    }

    @RequestMapping("/login-admin")
    public String adminLogin() {
        log.info("Admin login controller");
        System.out.println("로그인 어드민");
        return "login-admin";
    }

    @RequestMapping("/login-user")
    public String loginUser() {
        log.info("User login controller");
        return "login-user";
    }



}
