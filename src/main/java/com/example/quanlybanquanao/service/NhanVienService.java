package com.example.quanlybanquanao.service;

import com.example.quanlybanquanao.model.NhanVien;
import com.example.quanlybanquanao.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepository.findAll();
    }

    public NhanVien getNhanVienById(Integer id) {
        return nhanVienRepository.findById(id).orElse(null);
    }

    public void saveNhanVien(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
    }

    public void deleteNhanVien(Integer id) {
        nhanVienRepository.deleteById(id);
    }

    public List<NhanVien> getAvailableNhanViens() {
        return nhanVienRepository.findByTrangThaiFalse(); // Nhân viên chưa có tài khoản
    }

}
