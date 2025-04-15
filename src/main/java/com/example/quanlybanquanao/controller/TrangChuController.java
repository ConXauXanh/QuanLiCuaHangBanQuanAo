package com.example.quanlybanquanao.controller;

import com.example.quanlybanquanao.service.DonHangService;
import com.example.quanlybanquanao.service.KhachHangService;
import com.example.quanlybanquanao.service.NhanVienService;
import com.example.quanlybanquanao.service.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TrangChuController {

    private final NhanVienService nhanVienService;
    private final SanPhamService sanPhamService;
    private final DonHangService donHangService;
    private final KhachHangService khachHangService;

    @GetMapping("")
    public String trangChu(Model model) {
        model.addAttribute("nameFile", "home");
        return "layout";
    }

}
