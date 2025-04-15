package com.example.quanlybanquanao.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "[User]")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;
    private String username;
    private String password;
    private String role;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNV", referencedColumnName = "MaNV", unique = true) // Khóa ngoại tới bảng NhanVien
    private NhanVien nhanVien; // Liên kết tới NhanVien
}
