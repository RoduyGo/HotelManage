package com.example.QLKS.controllers;

import com.example.QLKS.dto.requestKhachHang.KhachHangCreationRequest;
import com.example.QLKS.dto.requestKhachHang.KhachHangUpdateRequest;
import com.example.QLKS.dto.requestNhanVien.NhanVienCreationRequest;
import com.example.QLKS.entities.KhachHang;
import com.example.QLKS.entities.NhanVien;
import com.example.QLKS.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/khachhangs")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;
    @PostMapping
    KhachHang createKhachHang(@RequestBody KhachHangCreationRequest request) {
        return khachHangService.createKhachHang(request);
    }
    @GetMapping
    List<KhachHang> getAllKhachHang() {
        return khachHangService.getAllKhachHang();
    }
    @GetMapping("/{maKhachHang}")
    KhachHang getKhachHang(@PathVariable int maKhachHang) {
        return khachHangService.getKhachHang(maKhachHang);
    }
    @DeleteMapping("/{maKhachHang}")
    String deleteKhachHang(@PathVariable int maKhachHang) {
        khachHangService.deleteKhachHang(maKhachHang);
        return "KhachHang deleted";
    }
    @PutMapping("/{maKhachHang}")
    KhachHang updateKhachhang(@PathVariable int maKhachHang, @RequestBody KhachHangUpdateRequest request) {
        return khachHangService.updateKhachHang(maKhachHang, request);
    }
}
