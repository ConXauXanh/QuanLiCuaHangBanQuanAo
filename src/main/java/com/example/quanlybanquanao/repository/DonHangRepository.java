package com.example.quanlybanquanao.repository;

import com.example.quanlybanquanao.model.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Integer> {

    // tinh tong
    @Query("SELECT SUM(d.tongTien) FROM DonHang d WHERE d.ngayDatHang BETWEEN :startDate AND :endDate")
    BigDecimal calculateRevenue(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // loc don hang theo khoang ngay
    @Query("SELECT d FROM DonHang d WHERE d.ngayDatHang BETWEEN :startDate AND :endDate")
    List<DonHang> getOrdersByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
