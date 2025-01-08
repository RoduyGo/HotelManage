package com.example.QLKS.service;

import com.example.QLKS.dto.requestNhanVien.NhanVienCreationRequest;
import com.example.QLKS.dto.requestNhanVien.NhanVienUpdateRequest;
import com.example.QLKS.entities.NhanVien;
import com.example.QLKS.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;
    public NhanVien createNhanVien(NhanVienCreationRequest request ){
        NhanVien nhanVien = new NhanVien();
        nhanVien.setHoTen(request.getHoTen());
        nhanVien.setChucVu(request.getChucVu());
        nhanVien.setSoDienThoai(request.getSoDienThoai());
        nhanVien.setLuong(request.getLuong());

        return nhanVienRepository.save(nhanVien);
    }
    public List<NhanVien> getAllNhanVien(){
        return nhanVienRepository.findAll();
    }
    public NhanVien getNhanVien(int maNhanVien){
        return nhanVienRepository.findById(maNhanVien).orElseThrow(()-> new RuntimeException("Khong tim thay nhan vien"));
    }
    public NhanVien updateNhanVien(int maNhanVien, NhanVienUpdateRequest request){
        NhanVien nhanVien = getNhanVien(maNhanVien);
        nhanVien.setHoTen(request.getHoTen());
        nhanVien.setChucVu(request.getChucVu());
        nhanVien.setSoDienThoai(request.getSoDienThoai());
        nhanVien.setLuong(request.getLuong());

        return nhanVienRepository.save(nhanVien);
    }
    public void deleteNhanVien(int maNhanVien){
        nhanVienRepository.deleteById(maNhanVien);
    }
}
