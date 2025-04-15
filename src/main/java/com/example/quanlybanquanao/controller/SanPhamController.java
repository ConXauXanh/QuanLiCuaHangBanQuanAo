package com.example.quanlybanquanao.controller;

import com.example.quanlybanquanao.model.SanPham;
import com.example.quanlybanquanao.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/hien-thi")
    public String showList(Model model) {
        List<SanPham> sanphams = sanPhamService.getAllSanPhams();
        model.addAttribute("sanphams", sanphams);
        model.addAttribute("nameFile", "listSanPham"); // Gọi file listSanPham.html trong layout
        return "layout";
    }

    // Thêm sản phẩm
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("nameFile", "addSanPham"); // Gọi file addSanPham.html trong layout
        return "layout";
    }

    @PostMapping("/save")
    public String saveSanPham(@ModelAttribute("sanPham") SanPham sanPham) {
        sanPhamService.saveSanPham(sanPham);
        return "redirect:/san-pham/hien-thi"; // Redirect để tránh reload form
    }

    // Sửa sản phẩm
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        model.addAttribute("sanPham", sanPham);
        model.addAttribute("nameFile", "addSanPham"); // Dùng lại form thêm để sửa
        return "layout";
    }

    @PostMapping("/update")
    public String updateSanPham(@ModelAttribute("sanPham") SanPham sanPham) {
        sanPhamService.updateSanPham(sanPham);
        return "redirect:/san-pham/hien-thi";
    }

    // Xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String deleteSanPham(@PathVariable("id") int id) {
        sanPhamService.deleteSanPham(id);
        return "redirect:/san-pham/hien-thi";
    }

}
