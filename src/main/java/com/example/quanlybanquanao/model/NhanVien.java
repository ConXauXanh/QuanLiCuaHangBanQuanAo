package com.example.quanlybanquanao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NhanVien")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNV")
    private Integer maNV;

    @Column(name = "TenNV")
    private String tenNV;

    @Column(name = "Sdt")
    private String sdt;

    @Column(name = "Email")
    private String email;

    @Column(name = "ChucVu")
    private String chucVu; // "Quản lý" hoặc "Nhân viên"

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(nullable = false)
    private Boolean trangThai = false; // Mặc định là chưa có tài khoản

    @OneToOne(mappedBy = "nhanVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    // Getters and Setters
}

