package com.example.QLKS.repository;

import com.example.QLKS.TrangThai.TrangThaiPhong;
import com.example.QLKS.entities.Phong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhongRepository extends JpaRepository<Phong, Integer> {
    List<Phong> findByTrangThaiPhong(TrangThaiPhong trangThaiPhong);
}
