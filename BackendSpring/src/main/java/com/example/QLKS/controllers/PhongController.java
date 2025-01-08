package com.example.QLKS.controllers;


import com.example.QLKS.TrangThai.TrangThaiPhong;
import com.example.QLKS.dto.requestPhong.PhongCreationRequest;
import com.example.QLKS.dto.requestPhong.PhongUpdateRequest;
import com.example.QLKS.entities.Phong;
import com.example.QLKS.service.PhongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phongs")
public class PhongController {
    @Autowired
    private PhongService phongService;
    @PostMapping
    Phong createPhong(@RequestBody PhongCreationRequest request) {
        return phongService.createPhong(request);
    }
    @GetMapping
    List<Phong> getAllPhongs() {
        return phongService.getAllPhong();
    }
    @GetMapping("/{maPhong}")
    Phong getPhong(@PathVariable int maPhong) {
        return phongService.getPhong(maPhong);
    }
    @PutMapping("/{maPhong}")
    Phong updatePhong(@PathVariable int maPhong, @RequestBody PhongUpdateRequest request) {
        return phongService.updatePhong(maPhong, request);
    }
    @DeleteMapping("/{maPhong}")
    String deletePhong(@PathVariable int maPhong) {
        phongService.deletePhong(maPhong);
        return "Phòng đã bị xóa thành công";

    }
    @GetMapping("/timphong/{trangThaiPhong}")
    List<Phong> getPhongByTrangThai(@PathVariable TrangThaiPhong trangThaiPhong) {
        return phongService.getPhongByTrangThai(trangThaiPhong);
    }

}