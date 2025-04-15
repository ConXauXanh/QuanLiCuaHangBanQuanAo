package com.example.quanlybanquanao.controller;

import com.example.quanlybanquanao.model.KhachHang;
import com.example.quanlybanquanao.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/khach-hang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    // Trang danh sách khách hàng
    @GetMapping("/hien-thi")
    public String listKhachHang(Model model) {
        model.addAttribute("khachHangs", khachHangService.getAllKhachHang());
        model.addAttribute("nameFile", "listKhachHang");
        return "layout";
    }

    // Trang thêm mới khách hàng
    @GetMapping("/add")
    public String addKhachHangForm(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("nameFile", "addKhachHang");
        return "layout";
    }

    // Lưu khách hàng mới hoặc cập nhật
    @PostMapping("/save")
    public String saveKhachHang(@ModelAttribute KhachHang khachHang) {
        khachHangService.saveKhachHang(khachHang);
        return "redirect:/khach-hang/hien-thi";
    }

    // Trang sửa thông tin khách hàng
    @GetMapping("/edit/{id}")
    public String editKhachHangForm(@PathVariable("id") Integer maKH, Model model) {
        model.addAttribute("khachHang", khachHangService.getKhachHangById(maKH).orElse(null));
        model.addAttribute("nameFile", "addKhachHang");
        return "layout";
    }

    // Xóa khách hàng
    @GetMapping("/delete/{id}")
    public String deleteKhachHang(@PathVariable("id") Integer maKH) {
        khachHangService.deleteKhachHang(maKH);
        return "redirect:/khach-hang/hien-thi";
    }

}
