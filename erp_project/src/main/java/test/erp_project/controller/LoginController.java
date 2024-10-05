package test.erp_project.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import test.erp_project.dto.user_dto.UserLoginDto;
import test.erp_project.dto.user_dto.UserSearchDto;
import test.erp_project.service.UserService;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("UserLoginDto", new UserLoginDto());
        log.info("Login controller");

        return "login-user";
    }

    @GetMapping("/login-user")
    public String loginUser(Model model) {
        model.addAttribute("UserLoginDto", new UserLoginDto());
        log.info("User login controller");
        return "login-user";
    }

    @GetMapping("/login-admin")
    public String adminLogin(Model model) {
        model.addAttribute("UserLoginDto", new UserLoginDto());
        log.info("Admin login controller");

        return "login-admin";
    }


    @PostMapping("/login-user")
    public String loginUser(UserLoginDto userLoginDto, Model model) {

        //admin으로 로그인을 실행했을경우 실행
        if(userLoginDto.getUserId().equals("admin")){

            // 검증로직추가필요.

            return "redirect:/login-user";
        }

        boolean checkLogin = userService.checkUser(userLoginDto);
        if(checkLogin) {
            return "/user/s";
        }

        return "redirect:/login-user";
    }

    @PostMapping("/login-admin")
    public String adminLogin(UserLoginDto userLoginDto, Model model) {

        //관리자 페이지에서 admin으로 로그인 하지 않을경우 실행
        if(!userLoginDto.getUserId().equals("admin")){
            return "redirect:/login-admin";
        }

        boolean checkLogin = userService.checkUser(userLoginDto);
        if(checkLogin) {
            List< UserSearchDto> userSearchDtoList = userService.getAllUserInfo();
            model.addAttribute("UserSearchDtoList", userSearchDtoList);
            return "redirect:/user/search";
        }

        return "redirect:/login-admin";
    }



}
