package com.example.quanlybanquanao.service;

import com.example.quanlybanquanao.model.DonHang;
import com.example.quanlybanquanao.repository.DonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ThongKeService {

    @Autowired
    private DonHangRepository donHangRepository;

    public BigDecimal getRevenue(LocalDate startDate, LocalDate endDate) {
        return donHangRepository.calculateRevenue(startDate, endDate);
    }

    public List<DonHang> getOrders(LocalDate startDate, LocalDate endDate) {
        return donHangRepository.getOrdersByDateRange(startDate, endDate);
    }
}