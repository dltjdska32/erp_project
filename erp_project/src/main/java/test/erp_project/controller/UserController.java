package test.erp_project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import test.erp_project.dto.user_dto.UserJoinDto;
import test.erp_project.dto.user_dto.UserSearchDto;
import test.erp_project.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("UserJoinDto", new UserJoinDto());

        log.info("join");
        System.out.println("회원가입 페이지");
        return "./user/join";
    }

    @GetMapping("/search")
    public String search(Model model) {
        List<UserSearchDto> userInfoList = userService.getAllUserInfo();

        model.addAttribute("userInfoList", userInfoList);
        log.info("userInfoList {} ", userInfoList);
        return "./user/search";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("UserJoinDto") UserJoinDto userJoinDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "./user/join"; // 오류가 있을 경우 다시 폼으로
        }

        // 정상 처리 로직
        userService.saveUser(userJoinDto);

        return "redirect:/";
    }


}
