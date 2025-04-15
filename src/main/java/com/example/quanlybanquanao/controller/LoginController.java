package com.example.quanlybanquanao.controller;

import com.example.quanlybanquanao.dto.UserLoginDTO;
import com.example.quanlybanquanao.model.User;
import com.example.quanlybanquanao.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage( Model model) {
        model.addAttribute("userLogin", new UserLoginDTO());
        return "login"; // login.html
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("userLogin") UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        HttpSession session,
                        Model model) {
        try {
            if (bindingResult.hasErrors()) {
                return "login"; // Lỗi validation
            }
            // Xác thực người dùng
            User user = userService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword());
            // Lưu thông tin vào Session
            session.setAttribute("user", user);
            return "redirect:/"; // Chuyển đến trang chính
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            // Thêm thông báo lỗi vào Model
            model.addAttribute("errorMessage", e.getMessage());
            return "login"; // Quay lại trang login với thông báo lỗi
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
