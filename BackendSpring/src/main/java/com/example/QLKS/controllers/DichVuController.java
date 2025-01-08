package com.example.QLKS.controllers;

import com.example.QLKS.dto.requestDichVu.DichVuCreateRequest;
import com.example.QLKS.dto.requestDichVu.DichVuUpdateRequest;
import com.example.QLKS.dto.requestKhachHang.KhachHangUpdateRequest;
import com.example.QLKS.entities.DichVu;
import com.example.QLKS.entities.KhachHang;
import com.example.QLKS.service.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dichvus")
public class DichVuController {
    @Autowired
    private DichVuService dichVuService;
    @PostMapping
    DichVu createDichVu(@RequestBody DichVuCreateRequest request) {
        return dichVuService.createDichVu(request);

    }
    @GetMapping
    List<DichVu> getAllDichVu() {
        return dichVuService.getAllDichVu();
    }
    @GetMapping("/{maDichVu}")
    DichVu getDichVu(@PathVariable int maDichVu) {
        return dichVuService.getDichVu(maDichVu);
    }
    @DeleteMapping("/{maDichVu}")
    String deleteDichVu(@PathVariable int maDichVu) {
        dichVuService.deleteDichVu(maDichVu);
        return "Dich Vu deleted";
    }
    @PutMapping("/{maDichVu}")
    DichVu updateDichVu(@PathVariable int maDichVu, @RequestBody DichVuUpdateRequest request) {
        return dichVuService.updateDichVu(maDichVu, request);
    }
}
