package com.example.QLKS.service;

import com.example.QLKS.dto.requestHoaDon.BCPhong;
import com.example.QLKS.dto.requestHoaDon.DoanhThuNgay;
import com.example.QLKS.dto.requestHoaDon.HoaDonCreationRequest;
import com.example.QLKS.dto.requestHoaDon.HoaDonUpdateRequest;
import com.example.QLKS.entities.Booking;
import com.example.QLKS.entities.HoaDon;
import com.example.QLKS.repository.BookingRepository;
import com.example.QLKS.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;

    public HoaDon createHoaDon(HoaDonCreationRequest request) {
        Booking booking = bookingRepository.findById(request.getMaDatPhong())
                .orElseThrow(()-> new RuntimeException(" Không tồn tại Booking này "));
        double TongTien = bookingService.tinhTongBooking(request.getMaDatPhong());

        HoaDon hoaDon = new HoaDon();
        hoaDon.setBooking(booking);
        hoaDon.setThoiGianLapHoaDon(new Date());
        hoaDon.setTongTien(TongTien);
        hoaDon.setTrangThaiThanhToan(HoaDon.TrangThaiThanhToan.chua_thanh_toan);
        hoaDon.setPhuongThucThanhToan(request.getPhuongThucThanhToan());

        return hoaDonRepository.save(hoaDon);
    }
    public HoaDon getHoaDon(int maHoaDon) {
        return hoaDonRepository.findById(maHoaDon)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy hoa don nay"));
    }
    public List<HoaDon> getHoaDonList() {
        return hoaDonRepository.findAll();
    }
    public HoaDon updateHoaDon(int maHoaDon , HoaDonUpdateRequest request) {
        HoaDon hoaDon = hoaDonRepository.findById(maHoaDon)
                .orElseThrow(()-> new RuntimeException(" Không tìm thấy hóa đơn này "));
        if (request.getTrangThaiThanhToan() != null) {
            hoaDon.setTrangThaiThanhToan(request.getTrangThaiThanhToan());
        }
        if (request.getPhuongThucThanhToan() != null) {
            hoaDon.setPhuongThucThanhToan(request.getPhuongThucThanhToan());
        }

        return hoaDonRepository.save(hoaDon);
    }
    public void deleteHoaDon(int maHoaDon) {
        hoaDonRepository.deleteById(maHoaDon);
    }
    public List<HoaDon> getAllHoaDon() {
        return hoaDonRepository.findAll();
    }
    public List<DoanhThuNgay> getDailyRevenue() {
        List<Object[]> results = hoaDonRepository.getDoanhThu();
        return results.stream()
                .map(result -> new DoanhThuNgay((Integer) result[0], (Integer) result[1], (Integer) result[2], (Double) result[3]))
                .collect(Collectors.toList());
    }
    public List<BCPhong> getRoomTypeBookings() {
        return hoaDonRepository.LoaiPhongUaThich().stream()
                .map(result -> new BCPhong((String) result[0], ((Long) result[1]).intValue()))
                .collect(Collectors.toList());
    }

}