package com.example.quanlybanquanao.controller;

import com.example.quanlybanquanao.model.NhanVien;
import com.example.quanlybanquanao.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/hien-thi")
    public String listNhanVien(Model model, Authentication authentication) {
        // Lấy thông tin vai trò người dùng từ đối tượng Authentication
        String role = authentication.getAuthorities().toString();
        System.out.println("Vai trò của người dùng:" + role);

        // Kiểm tra nếu người dùng có quyền ROLE_EMPLOYEE thì trả về giao diện 403
        if ("[ROLE_Admin]".equals(role)) {
            model.addAttribute("nhanviens", nhanVienService.getAllNhanVien());
            model.addAttribute("nameFile", "listNhanVien");
        } else {
            model.addAttribute("nameFile", "403");
        }
        return "layout"; // Trả về giao diện list nhân viên
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanvien", new NhanVien());
        model.addAttribute("nameFile", "addNhanVien");
        return "layout";
    }

    @PostMapping("/save")
    public String saveNhanVien(@ModelAttribute NhanVien nhanVien) {
        nhanVienService.saveNhanVien(nhanVien);
        return "redirect:/nhan-vien/hien-thi";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("nhanvien", nhanVienService.getNhanVienById(id));
        model.addAttribute("nameFile", "addNhanVien");
        return "layout";
    }

    @GetMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable Integer id) {
        nhanVienService.deleteNhanVien(id);
        return "redirect:/nhan-vien/hien-thi";
    }

}

