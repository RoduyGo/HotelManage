package com.example.QLKS.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "SuDungDichVu")
public class SuDungDichVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maSuDungDichVu")
    private int maSuDungDichVu;

    @ManyToOne
    @JoinColumn(name = "maDichVu")
    private DichVu dichVu;


    @ManyToOne
    @JoinColumn(name = "maDatPhong")
    @JsonIgnore
    private Booking booking;

    @Column(name = "soLuong")
    private int soLuong;

    public int getMaSuDungDichVu() {
        return maSuDungDichVu;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }


    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}

