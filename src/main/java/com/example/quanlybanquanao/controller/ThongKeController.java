package com.example.quanlybanquanao.controller;

import com.example.quanlybanquanao.model.DonHang;
import com.example.quanlybanquanao.service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/thong-ke")
public class ThongKeController {

    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("")
    public String showThongKePage(Model model) {
        model.addAttribute("startDate", LocalDate.now().minusDays(7)); // Mặc định 7 ngày trước
        model.addAttribute("endDate", LocalDate.now()); // Đến ngày hiện tại
        model.addAttribute("nameFile", "thongke");
        return "layout";
    }

    @PostMapping("/doanh-thu")
    public String calculateRevenue(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                   @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                   Model model) {
        BigDecimal revenue = thongKeService.getRevenue(startDate, endDate);
        List<DonHang> orders = thongKeService.getOrders(startDate, endDate);

        model.addAttribute("revenue", revenue);
        model.addAttribute("orders", orders);
        model.addAttribute("nameFile", "thongke");
        return "layout";
    }
}
