package com.example.QLKS.dto.requestHoaDon;

import com.example.QLKS.entities.HoaDon;

public class HoaDonUpdateRequest {
    private int maHoaDon;
    private HoaDon.TrangThaiThanhToan trangThaiThanhToan;
    private HoaDon.PhuongThucThanhToan phuongThucThanhToan;

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public HoaDon.TrangThaiThanhToan getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(HoaDon.TrangThaiThanhToan trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public HoaDon.PhuongThucThanhToan getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(HoaDon.PhuongThucThanhToan phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }
}
