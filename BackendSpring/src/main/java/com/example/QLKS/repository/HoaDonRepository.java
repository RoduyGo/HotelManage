package com.example.QLKS.repository;

import com.example.QLKS.entities.Booking;
import com.example.QLKS.entities.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    HoaDon findByBooking(Booking booking);
    @Query("SELECT YEAR(hd.thoiGianLapHoaDon) as year," +
            " MONTH(hd.thoiGianLapHoaDon) as month," +
            " DAY(hd.thoiGianLapHoaDon) as day," +
            " SUM(hd.tongTien) as revenue FROM HoaDon hd GROUP BY YEAR(hd.thoiGianLapHoaDon)," +
            " MONTH(hd.thoiGianLapHoaDon)," +
            " DAY(hd.thoiGianLapHoaDon)")
    List<Object[]> getDoanhThu();
    @Query("SELECT b.booking.phong.loaiPhong as tenLoaiPhong, COUNT(b) as soLuong " +
            "FROM HoaDon b " +
            "GROUP BY b.booking.phong.loaiPhong " +
            "ORDER BY soLuong DESC")
    List<Object[]> LoaiPhongUaThich();

}
