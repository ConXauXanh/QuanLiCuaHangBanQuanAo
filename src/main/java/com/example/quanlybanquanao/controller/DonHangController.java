package com.example.quanlybanquanao.controller;

import com.example.quanlybanquanao.model.DonHang;
import com.example.quanlybanquanao.model.KhachHang;
import com.example.quanlybanquanao.service.DonHangService;
import com.example.quanlybanquanao.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/don-hang")
public class DonHangController {

    @Autowired
    private DonHangService donHangService;
    @Autowired
    private KhachHangService khachHangService;

    // Hiển thị danh sách đơn hàng
    @GetMapping("/hien-thi")
    public String listDonHangs(Model model) {
        List<DonHang> donHangs = donHangService.getAllDonHangs();
        model.addAttribute("donHangs", donHangs);
        model.addAttribute("nameFile", "listDonHang"); // file listDonHang.html
        return "layout";
    }

    // Form dùng chung cho thêm + sửa
    @GetMapping("/add")
    public String showForm(@RequestParam(value = "id", required = false) Integer id, Model model) {
        DonHang donHang = (id != null) ? donHangService.getDonHangById(id) : new DonHang();
        List<KhachHang> khachHangList = khachHangService.getAllKhachHang();
        model.addAttribute("khachHangList", khachHangList);
        model.addAttribute("donHang", donHang);
        model.addAttribute("nameFile", "addDonHang"); // file addDonHang.html
        return "layout";
    }

    // Xử lý thêm hoặc cập nhật đơn hàng
    @PostMapping("/save")
    public String saveDonHang(@ModelAttribute("donHang") DonHang donHang, Model model) {
        donHangService.saveDonHang(donHang);
        // Redirect để tránh lỗi submit lại form và để hiển thị layout
        return "redirect:/don-hang/hien-thi";
    }

    // Xóa đơn hàng
    @GetMapping("/xoa/{id}")
    public String deleteDonHang(@PathVariable("id") Integer id) {
        donHangService.deleteDonHang(id);
        return "redirect:/don-hang/hien-thi";
    }

    @GetMapping("/edit/{id}")
    public String detailDonHang(@PathVariable("id") Integer id, Model model) {
        DonHang donHang = donHangService.getDonHangById(id);
        List<KhachHang> khachHangList = khachHangService.getAllKhachHang();
        model.addAttribute("khachHangList", khachHangList);
        model.addAttribute("donHang", donHang);
        model.addAttribute("nameFile", "addDonHang");
        return "layout";
    }

}
