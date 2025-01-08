package com.example.QLKS.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maDatPhong ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date ngayNhanPhong;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date ngayTraPhong;
    @ManyToOne
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHang;
    @ManyToOne
    @JoinColumn(name = "maPhong")
    private Phong phong;
    private double giaPhongThucTe;
    @Enumerated(EnumType.STRING)
    @Column(name = "trangThaiDatPhong")
    private TrangThaiDatPhong trangThaiDatPhong;
    @ManyToOne
    @JoinColumn(name = "maNhanVien")
    private NhanVien nhanVien;

    public void setMaDatPhong(int maDatPhong) {
        this.maDatPhong = maDatPhong;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SuDungDichVu> suDungDichVuList;
    public enum TrangThaiDatPhong {
        da_dat,
        dang_su_dung,
        da_tra_phong
    }

    public List<SuDungDichVu> getSuDungDichVuList() {
        return suDungDichVuList;
    }

    public void setSuDungDichVuList(List<SuDungDichVu> suDungDichVuList) {
        this.suDungDichVuList = suDungDichVuList;
    }

    public int getMaDatPhong() {
        return maDatPhong;
    }

    public Date getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public void setNgayNhanPhong(Date ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    public Date getNgayTraPhong() {
        return ngayTraPhong;
    }

    public void setNgayTraPhong(Date ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }


    public double getGiaPhongThucTe() {
        return giaPhongThucTe;
    }

    public void setGiaPhongThucTe(double giaPhongThucTe) {
        this.giaPhongThucTe = giaPhongThucTe;
    }

    public TrangThaiDatPhong getTrangThaiDatPhong() {
        return trangThaiDatPhong;
    }

    public void setTrangThaiDatPhong(TrangThaiDatPhong trangThaiDatPhong) {
        this.trangThaiDatPhong = trangThaiDatPhong;
    }
}