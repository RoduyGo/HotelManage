package com.example.QLKS.service;

import com.example.QLKS.TrangThai.TrangThaiPhong;
import com.example.QLKS.dto.requestPhong.PhongCreationRequest;
import com.example.QLKS.dto.requestPhong.PhongUpdateRequest;
import com.example.QLKS.entities.Phong;
import com.example.QLKS.repository.PhongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongService {
    @Autowired
    private PhongRepository phongRepository;

    public Phong createPhong(PhongCreationRequest request) {
        if (phongRepository.existsById(request.getMaPhong())) {
            throw new RuntimeException("Phòng này đã tồn tại ");
        }
        Phong phong = new Phong();
        phong.setMaPhong(request.getMaPhong());
        phong.setGiaPhong(request.getGiaPhong());
        phong.setLoaiPhong(request.getLoaiPhong());
        phong.setTrangThaiPhong(TrangThaiPhong.con_trong);
        return phongRepository.save(phong);
    }

    public List<Phong> getAllPhong() {
        return phongRepository.findAll();
    }

    public Phong getPhong(int maPhong) {
        return phongRepository.findById(maPhong)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng"));
    }

    public Phong updatePhong(int maPhong, PhongUpdateRequest request) {
        Phong phong = getPhong(maPhong);
        phong.setGiaPhong(request.getGiaPhong());
        if (request.getLoaiPhong() != null && !request.getLoaiPhong().isEmpty()) {
            phong.setLoaiPhong(request.getLoaiPhong());}
        if (request.getTrangThaiPhong() != null) {
            phong.setTrangThaiPhong(request.getTrangThaiPhong());}
        return phongRepository.save(phong);
    }

    public void deletePhong(int maPhong) {
        phongRepository.deleteById(maPhong);
    }
    public List<Phong> getPhongByTrangThai(TrangThaiPhong trangThaiPhong) {
        return phongRepository.findByTrangThaiPhong(trangThaiPhong);
    }
}