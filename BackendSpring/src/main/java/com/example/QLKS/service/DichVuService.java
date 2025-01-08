package com.example.QLKS.service;

import com.example.QLKS.dto.requestDichVu.DichVuCreateRequest;
import com.example.QLKS.dto.requestDichVu.DichVuUpdateRequest;
import com.example.QLKS.entities.DichVu;
import com.example.QLKS.repository.DichVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DichVuService {
    @Autowired
    private DichVuRepository dichVuRepository;
    public DichVu createDichVu(DichVuCreateRequest request) {
        DichVu dichVu = new DichVu();
        dichVu.setMaDichVu(request.getMaDichVu());
        dichVu.setTenDichVu(request.getTenDichVu());
        dichVu.setGiaDichVu(request.getGiaDichVu());
        return dichVuRepository.save(dichVu);


    }
    public List<DichVu> getAllDichVu() {
        return dichVuRepository.findAll();
    }
    public DichVu getDichVu(int maDichVu) {
        return dichVuRepository.findById(maDichVu).orElseThrow(()-> new RuntimeException("Khong tim thay dich vu "));
    }
    public void deleteDichVu(int maDichVu) {
        dichVuRepository.deleteById(maDichVu);
    }
    public DichVu updateDichVu(int maDichVu ,DichVuUpdateRequest request) {
        DichVu dichVu = getDichVu(maDichVu);
        dichVu.setMaDichVu(request.getMaDichVu());
        dichVu.setTenDichVu(request.getTenDichVu());
        dichVu.setGiaDichVu(request.getGiaDichVu());
        return dichVuRepository.save(dichVu);
    }
}
