package com.example.QLKS.controllers;

import com.example.QLKS.dto.requestSuDungDichVu.SDDVCreationRequest;
import com.example.QLKS.dto.requestSuDungDichVu.SDDVUpdateRequest;
import com.example.QLKS.dto.requestSuDungDichVu.SuDungDichVuResponse;
import com.example.QLKS.entities.SuDungDichVu;
import com.example.QLKS.service.SuDungDichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sudungdichvu")
public class SuDungDichVuController {
    @Autowired
    private SuDungDichVuService suDungDichVuService;
    @PostMapping
    SuDungDichVu suDungDichVu(@RequestBody SDDVCreationRequest request) {
        return suDungDichVuService.createSuDungDichVu(request);
    }
    @GetMapping
    public ResponseEntity<List<SuDungDichVuResponse>> getAllSuDungDichVu() {
        List<SuDungDichVuResponse> suDungDichVuResponses = suDungDichVuService.getSuDungDichVuResponses();
        return ResponseEntity.ok(suDungDichVuResponses);
    }
    @GetMapping("/{maSuDungDichVu}")
    public ResponseEntity<SuDungDichVuResponse> getSuDungDichVu(@PathVariable int maSuDungDichVu) {
        SuDungDichVuResponse suDungDichVuResponse = suDungDichVuService.getSuDungDichVu(maSuDungDichVu);
        return ResponseEntity.ok(suDungDichVuResponse);
    }
    @DeleteMapping("/{maSuDungDichVu}")
    String deleteSuDungDichVu(@PathVariable int maSuDungDichVu) {
        suDungDichVuService.deleteSuDungDichVu(maSuDungDichVu);
        return " Order này bị xóa thành công";
    }
    @PutMapping("/{maSuDungDichVu}")
    private SuDungDichVu updateSuDungDichVu(@PathVariable int maSuDungDichVu, @RequestBody SDDVUpdateRequest request) {
        return suDungDichVuService.updateSuDungDichVu(maSuDungDichVu, request);
    }
    @GetMapping("/{maSuDungDichVu}/tongtien")
    public ResponseEntity<String> getTongTien(@PathVariable int maSuDungDichVu) {
        double TongTien = suDungDichVuService.TongTien(maSuDungDichVu);
        String message = "Tổng tiền của order là: " + TongTien;
        return ResponseEntity.ok(message);
    }
}
