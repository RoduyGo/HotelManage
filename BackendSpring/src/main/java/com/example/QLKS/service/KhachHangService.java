package com.example.QLKS.service;


import com.example.QLKS.dto.requestKhachHang.KhachHangCreationRequest;
import com.example.QLKS.dto.requestKhachHang.KhachHangUpdateRequest;
import com.example.QLKS.entities.KhachHang;
import com.example.QLKS.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;
    public KhachHang createKhachHang(KhachHangCreationRequest request) {
        KhachHang khachHang = new KhachHang();
        khachHang.setHoTen(request.getHoTen());
        khachHang.setDiaChi(request.getDiaChi());
        khachHang.setSoDienThoai(request.getSoDienThoai());
        khachHang.setNamSinh(request.getNamSinh());
        khachHang.setGiayToTuyThan(request.getGiayToTuyThan());

        return khachHangRepository.save(khachHang);

    }
    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }
    public KhachHang getKhachHang(int maKhachHang) {
        return khachHangRepository.findById(maKhachHang).orElseThrow(()-> new RuntimeException("Khong tim thay khach hang nay"));

    }
    public void deleteKhachHang(int maKhachHang) {
    khachHangRepository.deleteById(maKhachHang);}
    public KhachHang updateKhachHang(int maKhachHang, KhachHangUpdateRequest request) {
        KhachHang khachHang = getKhachHang(maKhachHang);
        khachHang.setHoTen(request.getHoTen());
        khachHang.setDiaChi(request.getDiaChi());
        khachHang.setNamSinh(request.getNamSinh());
        khachHang.setSoDienThoai(request.getSoDienThoai());
        khachHang.setGiayToTuyThan(request.getGiayToTuyThan());
        return khachHangRepository.save(khachHang);
    }
}
