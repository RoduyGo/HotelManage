package com.example.QLKS.dto.requestHoaDon;

import com.example.QLKS.entities.HoaDon;

public class HoaDonCreationRequest {
    private int maDatPhong;
    private HoaDon.PhuongThucThanhToan phuongThucThanhToan;

    public int getMaDatPhong() {
        return maDatPhong;
    }

    public void setMaDatPhong(int maDatPhong) {
        this.maDatPhong = maDatPhong;
    }

    public HoaDon.PhuongThucThanhToan getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(HoaDon.PhuongThucThanhToan phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }
}
