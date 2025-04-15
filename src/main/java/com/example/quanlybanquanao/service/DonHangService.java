package com.example.quanlybanquanao.service;

import com.example.quanlybanquanao.model.DonHang;
import com.example.quanlybanquanao.repository.DonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonHangService {

    @Autowired
    private DonHangRepository donHangRepository;

    public List<DonHang> getAllDonHangs() {
        return donHangRepository.findAll();
    }

    public DonHang getDonHangById(Integer id) {
        return donHangRepository.findById(id).orElse(null);
    }

    public DonHang saveDonHang(DonHang donHang) {
        return donHangRepository.save(donHang);
    }

    public void deleteDonHang(Integer id) {
        donHangRepository.deleteById(id);
    }
}
