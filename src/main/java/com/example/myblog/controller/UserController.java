package com.example.myblog.controller;

import com.example.myblog.entity.User;
import com.example.myblog.dto.JoinDto;
import com.example.myblog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/join")
    public String joinForm(@ModelAttribute JoinDto joinDto) {
        return "user/joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute JoinDto joinDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/joinForm";
        }
        userService.join(joinDto);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute User user) {
        return "user/loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
}
