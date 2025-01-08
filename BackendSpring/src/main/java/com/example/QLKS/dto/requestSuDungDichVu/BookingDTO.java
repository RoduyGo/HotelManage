package com.example.QLKS.dto.requestSuDungDichVu;

public class BookingDTO {
    private int maDatPhong;
    private int maPhong;

    public BookingDTO(int maDatPhong, int maPhong) {
        this.maDatPhong = maDatPhong;
        this.maPhong = maPhong;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getMaDatPhong() {
        return maDatPhong;
    }

    public void setMaDatPhong(int maDatPhong) {
        this.maDatPhong = maDatPhong;
    }
}
