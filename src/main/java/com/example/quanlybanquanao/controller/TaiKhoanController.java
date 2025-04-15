package com.example.quanlybanquanao.controller;

import com.example.quanlybanquanao.service.NhanVienService;
import com.example.quanlybanquanao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tai-khoan")
public class TaiKhoanController {
    @Autowired
    private UserService userService;

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/hien-thi")
    public String showUserList(Model model, Authentication authentication) {
        // Lấy thông tin vai trò người dùng từ đối tượng Authentication
        String role = authentication.getAuthorities().toString();
        System.out.println("Vai trò của người dùng:" + role);

        // Kiểm tra nếu người dùng có quyền ROLE_EMPLOYEE thì trả về giao diện 403
        if ("[ROLE_Admin]".equals(role)) {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("nhanViens", nhanVienService.getAvailableNhanViens()); // Nhân viên chưa có tài khoản
            model.addAttribute("nameFile", "taiKhoan");
        }else {
            model.addAttribute("nameFile", "403");
        }
        return "layout"; // Trả về giao diện
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String role,
                          @RequestParam Integer maNhanVien) {
        userService.createUserForNhanVien(maNhanVien, username, password, role);
        return "redirect:/tai-khoan/hien-thi";
    }
}
