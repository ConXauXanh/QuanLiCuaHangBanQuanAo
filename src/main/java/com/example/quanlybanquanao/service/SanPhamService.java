package com.example.quanlybanquanao.service;

import com.example.quanlybanquanao.model.SanPham;
import com.example.quanlybanquanao.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    public List<SanPham> getAllSanPhams() {
        return sanPhamRepository.findAll();
    }

    public void saveSanPham(SanPham sanPham) {
        sanPhamRepository.save(sanPham);
    }

    public SanPham getSanPhamById(Integer id) {
        return sanPhamRepository.findById(id).orElse(null);
    }

    public void updateSanPham(SanPham sanPham) {
        sanPhamRepository.save(sanPham);
    }

    public void deleteSanPham(Integer id) {
        sanPhamRepository.deleteById(id);
    }
}

