package test.erp_project.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import test.erp_project.dto.user_dto.UserJoinDto;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("UserJoinDto", new UserJoinDto());

        log.info("join");
        System.out.println("회원가입 페이지");
        return "./user/join";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("UserJoinDto") UserJoinDto userJoinDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "./user/join"; // 오류가 있을 경우 다시 폼으로
        }
        
        
        // 정상 처리 로직
        // 데이터 베이스에 저장하는 로직추가필요
        
        return "redirect:/";
    }

}
