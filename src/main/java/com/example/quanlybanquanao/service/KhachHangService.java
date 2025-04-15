package com.example.quanlybanquanao.service;

import com.example.quanlybanquanao.model.KhachHang;
import com.example.quanlybanquanao.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }

    public Optional<KhachHang> getKhachHangById(Integer maKH) {
        return khachHangRepository.findById(maKH);
    }

    public KhachHang saveKhachHang(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    public void deleteKhachHang(Integer maKH) {
        khachHangRepository.deleteById(maKH);
    }
}
