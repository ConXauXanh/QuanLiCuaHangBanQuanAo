package com.example.quanlybanquanao.repository;

import com.example.quanlybanquanao.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
    // Có thể thêm các truy vấn tùy chỉnh nếu cần
    KhachHang findByEmail(String email);
    KhachHang findBySdt(String sdt);
}
