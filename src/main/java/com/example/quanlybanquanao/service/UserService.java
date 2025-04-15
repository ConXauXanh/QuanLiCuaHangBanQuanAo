package com.example.quanlybanquanao.service;

import com.example.quanlybanquanao.model.NhanVien;
import com.example.quanlybanquanao.model.User;
import com.example.quanlybanquanao.repository.NhanVienRepository;
import com.example.quanlybanquanao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng.");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Sai mật khẩu.");
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Tao tai khoan
    public User createUserForNhanVien(Integer maNhanVien, String username, String password, String role) {
        NhanVien nhanVien = nhanVienRepository.findById(maNhanVien)
                .orElseThrow(() -> new IllegalArgumentException("Nhân viên không tồn tại"));

        if (nhanVien.getTrangThai()) {
            throw new IllegalStateException("Nhân viên này đã có tài khoản");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Nên mã hóa bằng BCrypt
        user.setRole(role);
        user.setNhanVien(nhanVien);

        nhanVien.setTrangThai(true); // Cập nhật trạng thái nhân viên
        nhanVienRepository.save(nhanVien);

        return userRepository.save(user); // Lưu tài khoản mới
    }

}
