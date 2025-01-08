package com.example.QLKS.controllers;

import com.example.QLKS.dto.requestNhanVien.NhanVienCreationRequest;
import com.example.QLKS.dto.requestNhanVien.NhanVienUpdateRequest;
import com.example.QLKS.entities.NhanVien;
import com.example.QLKS.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nhanviens")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @PostMapping
    NhanVien createNhanVien(@RequestBody NhanVienCreationRequest request){
       return nhanVienService.createNhanVien(request);
    }
    @GetMapping
    List<NhanVien> getAllNhanVien(){
        return nhanVienService.getAllNhanVien();
    }
    @GetMapping("/{maNhanVien}")
    NhanVien getNhanVien(@PathVariable int maNhanVien){
        return nhanVienService.getNhanVien(maNhanVien);
    }
    @PutMapping("/{maNhanVien}")
    NhanVien updateNhanVien(@PathVariable int maNhanVien, @RequestBody NhanVienUpdateRequest request){
        return nhanVienService.updateNhanVien(maNhanVien,request);
    }
    @DeleteMapping("/{maNhanVien}")
    String deleteNhanVien(@PathVariable int maNhanVien){
        nhanVienService.deleteNhanVien(maNhanVien);
        return "Nhân viên đã bị xóa ";
    }
}
