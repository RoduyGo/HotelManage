package com.example.QLKS.service;

import com.example.QLKS.TrangThai.TrangThaiPhong;
import com.example.QLKS.dto.requestBooking.BookingCreateRequest;
import com.example.QLKS.dto.requestBooking.BookingUpdateRequest;
import com.example.QLKS.entities.*;
import com.example.QLKS.repository.BookingRepository;
import com.example.QLKS.repository.KhachHangRepository;
import com.example.QLKS.repository.NhanVienRepository;
import com.example.QLKS.repository.PhongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private PhongRepository phongRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public Booking createBooking(BookingCreateRequest request) {
        KhachHang khachHang = khachHangRepository.findById(request.getMaKhachHang()).orElseThrow(() -> new RuntimeException("Khach hang khong ton tai"));
        NhanVien nhanVien = nhanVienRepository.findById(request.getMaNhanVien()).orElseThrow(() -> new RuntimeException("Nhan vien ton tai"));
        Phong phong = phongRepository.findById(request.getMaPhong()).orElseThrow(()-> new RuntimeException("Phòng không tồn tại"));
        if (phong.getTrangThaiPhong() != TrangThaiPhong.con_trong) {
            throw new RuntimeException("Phòng không ở trạng thái còn trống. Vui lòng chọn phòng khác.");
        }

        Booking booking = new Booking();
        booking.setNgayNhanPhong(request.getNgayNhanPhong());
        booking.setNgayTraPhong(request.getNgayTraPhong());
        booking.setKhachHang(khachHang);
        booking.setPhong(phong);
        booking.setNhanVien(nhanVien);
        booking.setGiaPhongThucTe(request.getGiaPhongThucTe());
        booking.setTrangThaiDatPhong(Booking.TrangThaiDatPhong.valueOf(request.getTrangThaiDatPhong()));
        Booking savedBooking = bookingRepository.save(booking);


        phong.setTrangThaiPhong(TrangThaiPhong.da_dat);
        phongRepository.save(phong);

        return savedBooking;
    }
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }
    public Booking updateBooking( int maDatPhong , BookingUpdateRequest request){
        Booking booking = bookingRepository.findById(maDatPhong)
                .orElseThrow(()-> new RuntimeException("Booking này không tồn tại"));
        if (request.getNgayNhanPhong() != null) {
            booking.setNgayNhanPhong(request.getNgayNhanPhong());
        }
        if (request.getNgayTraPhong() != null) {
            booking.setNgayTraPhong(request.getNgayTraPhong());
        }
        if (request.getGiaPhongThucTe() != 0) {
            booking.setGiaPhongThucTe(request.getGiaPhongThucTe());
        }
        if (request.getTrangThaiDatPhong() != null) {
            if (Booking.TrangThaiDatPhong.valueOf(request.getTrangThaiDatPhong()) == Booking.TrangThaiDatPhong.da_tra_phong) {
                Phong phong = booking.getPhong();
                phong.setTrangThaiPhong(TrangThaiPhong.con_trong);
                phongRepository.save(phong);
            }

            booking.setTrangThaiDatPhong(Booking.TrangThaiDatPhong.valueOf(request.getTrangThaiDatPhong()));
        }
        if (request.getMaKhachHang() > 0) {
            KhachHang khachHang = khachHangRepository.findById(request.getMaKhachHang())
                    .orElseThrow(() -> new RuntimeException("Khách hàng không tồn tại"));
            booking.setKhachHang(khachHang);
        }
        if (request.getMaPhong() > 0) {
            Phong phong = phongRepository.findById(request.getMaPhong())
                    .orElseThrow(() -> new RuntimeException("Phòng không tồn tại"));
            booking.setPhong(phong);
        }
        if (request.getMaNhanVien() > 0) {
            NhanVien nhanVien = nhanVienRepository.findById(request.getMaNhanVien())
                    .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại"));
            booking.setNhanVien(nhanVien);
        }
        return bookingRepository.save(booking);
    }
    public Booking getBooking(int maDatPhong) {
        return bookingRepository.findById(maDatPhong).orElseThrow(()-> new RuntimeException("Không tìm thấy Booking này"));
    }
    public void deleteBooking(int maDatPhong) {
        bookingRepository.deleteById(maDatPhong);
    }
    public double tinhTongBooking(int maDatPhong) {
        Booking booking = bookingRepository.findById(maDatPhong)
                .orElseThrow(()-> new RuntimeException("Không tồn tại booking này "));
        double TongTien = 0 ;

        if(booking.getNgayNhanPhong() != null && booking.getNgayTraPhong() != null) {
            long thoigian  = Math.abs(booking.getNgayTraPhong().getTime() - booking.getNgayNhanPhong().getTime());
            long days = TimeUnit.DAYS.convert(thoigian, TimeUnit.MILLISECONDS);
            TongTien += days * booking.getGiaPhongThucTe();
        }
        if(booking.getSuDungDichVuList() != null) {
            for(SuDungDichVu suDungDichVu : booking.getSuDungDichVuList()){
                double giaDichVu = suDungDichVu.getDichVu().getGiaDichVu();
                int soLuong = suDungDichVu.getSoLuong();
                TongTien += giaDichVu * soLuong;
            }
        }
        return TongTien;
    }
}