package test.erp_project.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import test.erp_project.dto.user_dto.UserJoinDto;

@Controller
@Slf4j
public class UserController {

    @RequestMapping("/join")
    public String join() {
        log.info("join");
        System.out.println("회원가입 페이지");
        return "user/join";
    }

 /*   @PostMapping("/join")
    public String join(@Valid UserJoinDto userJoinDto, BindingResult bindingResult, Model model) {
        log.info("join");


        return "redirect:/";
    }*/

}
