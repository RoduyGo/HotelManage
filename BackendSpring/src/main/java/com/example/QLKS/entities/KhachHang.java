package com.example.QLKS.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "KhachHang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maKhachHang;

    private String hoTen;
    private String diaChi;
    private String soDienThoai;
    private LocalDate namSinh;
    private String giayToTuyThan;

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public LocalDate getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(LocalDate namSinh) {
        this.namSinh = namSinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }



    public String getGiayToTuyThan() {
        return giayToTuyThan;
    }

    public void setGiayToTuyThan(String giayToTuyThan) {
        this.giayToTuyThan = giayToTuyThan;
    }
}
