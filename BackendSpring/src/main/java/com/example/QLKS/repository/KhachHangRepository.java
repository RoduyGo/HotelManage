package com.example.QLKS.repository;

import com.example.QLKS.entities.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository  extends JpaRepository<KhachHang, Integer> {
}
