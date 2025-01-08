package com.example.QLKS.controllers;

import com.example.QLKS.dto.requestBooking.BookingCreateRequest;
import com.example.QLKS.dto.requestBooking.BookingUpdateRequest;
import com.example.QLKS.entities.Booking;
import com.example.QLKS.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping
    Booking createBooking(@RequestBody BookingCreateRequest request) {
        return bookingService.createBooking(request);
    }
    @GetMapping
    private List<Booking> getAllBookings() {
        return bookingService.getAllBooking();
    }
    @PutMapping("/{maDatPhong}")
    private Booking updateBooking(@PathVariable int maDatPhong, @RequestBody BookingUpdateRequest request) {
        Booking booking = bookingService.updateBooking(maDatPhong, request);

        return bookingService.updateBooking(maDatPhong,request);
    }
    @GetMapping("/{maDatPhong}")
    private Booking getBooking(@PathVariable int maDatPhong) {
        return bookingService.getBooking(maDatPhong);
    }
    @DeleteMapping("/{maDatPhong}")
    String deleteBooking(@PathVariable int maDatPhong) {
        bookingService.deleteBooking(maDatPhong);
        return "Booking  deleted";
    }
    @GetMapping("/{maDatPhong}/tongtien")
   public double tinhTongBooking(@PathVariable int maDatPhong) {

        return  bookingService.tinhTongBooking(maDatPhong);
    }

}
