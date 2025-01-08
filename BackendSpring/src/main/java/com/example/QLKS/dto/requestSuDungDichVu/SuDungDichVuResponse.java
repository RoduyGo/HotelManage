package com.example.QLKS.dto.requestSuDungDichVu;

public class SuDungDichVuResponse {
    private int maSuDungDichVu;
    private DichVuDTO dichVu;
    private BookingDTO bookingDTO;
    private int soLuong;

    public SuDungDichVuResponse(int maSuDungDichVu, DichVuDTO dichVu, BookingDTO bookingDTO, int soLuong) {
        this.maSuDungDichVu = maSuDungDichVu;
        this.dichVu = dichVu;
    this.bookingDTO = bookingDTO;

        this.soLuong = soLuong;
    }

    public int getMaSuDungDichVu() {
        return maSuDungDichVu;
    }

    public BookingDTO getBookingDTO() {
        return bookingDTO;
    }

    public void setBookingDTO(BookingDTO bookingDTO) {
        this.bookingDTO = bookingDTO;
    }

    public void setMaSuDungDichVu(int maSuDungDichVu) {
        this.maSuDungDichVu = maSuDungDichVu;
    }

    public DichVuDTO getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVuDTO dichVu) {
        this.dichVu = dichVu;
    }


    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
