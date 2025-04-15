package com.example.quanlybanquanao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DonHang")
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDonHang")
    private Integer maDonHang;// ttinh

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaKH")
    private KhachHang khachHang;

    @Column(name = "NgayDatHang")
    private LocalDate ngayDatHang;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "TongTien")
    private Double tongTien;

    // Quan hệ One-to-Many với ChiTietDonHang
    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ChiTietDonHang> chiTietDonHangs;

    // Quan hệ One-to-One với ThanhToan
    @OneToOne(mappedBy = "donHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ThanhToan thanhToan;

    // Quan hệ One-to-One với LichSuTraHang
    @OneToOne(mappedBy = "donHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LichSuTraHang lichSuTraHang;
}
