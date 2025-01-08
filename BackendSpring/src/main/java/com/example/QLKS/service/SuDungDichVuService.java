package com.example.QLKS.service;

import com.example.QLKS.dto.requestSuDungDichVu.*;
import com.example.QLKS.entities.Booking;
import com.example.QLKS.entities.DichVu;
import com.example.QLKS.entities.SuDungDichVu;
import com.example.QLKS.repository.BookingRepository;
import com.example.QLKS.repository.DichVuRepository;
import com.example.QLKS.repository.SuDungDichVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuDungDichVuService {
    @Autowired
    private SuDungDichVuRepository suDungDichVuRepository;
    @Autowired
    private DichVuRepository dichVuRepository;
    @Autowired
    private BookingRepository bookingRepository;
    public SuDungDichVu createSuDungDichVu(SDDVCreationRequest request){
        DichVu dichVu = dichVuRepository.findById(request.getMaDichVu()).orElseThrow(()-> new RuntimeException("Dich vu này không tồn tại"));
        Booking booking =  bookingRepository.findById(request.getMaDatPhong())
                .orElseThrow(() -> new RuntimeException("Booking này không tồn tại"));

        SuDungDichVu suDungDichVu = new SuDungDichVu();
        suDungDichVu.setDichVu(dichVu);
        suDungDichVu.setBooking(booking);
        suDungDichVu.setSoLuong(request.getSoLuong());
        return suDungDichVuRepository.save(suDungDichVu);

    }
    public List<SuDungDichVuResponse> getSuDungDichVuResponses() {
        List<SuDungDichVu> suDungDichVus = suDungDichVuRepository.findAll();
        return suDungDichVus.stream().map(suDungDichVu -> new SuDungDichVuResponse(
                suDungDichVu.getMaSuDungDichVu(),
                new DichVuDTO(
                        suDungDichVu.getDichVu().getMaDichVu(),
                        suDungDichVu.getDichVu().getTenDichVu(),
                        suDungDichVu.getDichVu().getGiaDichVu()
                ),
                new BookingDTO(
                        suDungDichVu.getBooking().getMaDatPhong(),
                        suDungDichVu.getBooking().getPhong().getMaPhong()
                ),

                suDungDichVu.getSoLuong()
        )).collect(Collectors.toList());
    }
    public void deleteSuDungDichVu(int maSuDungDichVu) {
        suDungDichVuRepository.deleteById(maSuDungDichVu);
    }
    public SuDungDichVuResponse getSuDungDichVu(int maSuDungDichVu) {
        SuDungDichVu suDungDichVu = suDungDichVuRepository.findById(maSuDungDichVu)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy order này"));
        return new SuDungDichVuResponse(
                suDungDichVu.getMaSuDungDichVu(),
                new DichVuDTO(
                        suDungDichVu.getDichVu().getMaDichVu(),
                        suDungDichVu.getDichVu().getTenDichVu(),
                        suDungDichVu.getDichVu().getGiaDichVu()
                ),
                new BookingDTO(
                        suDungDichVu.getBooking().getMaDatPhong(),
                        suDungDichVu.getBooking().getPhong().getMaPhong()
                ),
                suDungDichVu.getSoLuong()
        );
    }

    public SuDungDichVu updateSuDungDichVu(int maSuDungDichVu, SDDVUpdateRequest request ) {
        SuDungDichVu suDungDichVu = suDungDichVuRepository.findById(maSuDungDichVu).orElseThrow(()-> new RuntimeException("Oder này không tồn tại"));
        DichVu dichVu = dichVuRepository.findById(request.getMaDichVu())
                .orElseThrow(() -> new RuntimeException("Dich Vu không tồn tại"));

        suDungDichVu.setDichVu(dichVuRepository.findById(request.getMaDichVu()).orElseThrow(()-> new RuntimeException("Dich vu này không tồn tại")));

        suDungDichVu.setSoLuong(request.getSoLuong());
        return suDungDichVuRepository.save(suDungDichVu);
    }
    public double TongTien(int maSuDungDichVu){
        SuDungDichVu suDungDichVu = suDungDichVuRepository.findById(maSuDungDichVu).orElseThrow(()-> new RuntimeException("Order này không tồn tại"));
        double giaDichVu = suDungDichVu.getDichVu().getGiaDichVu();
        int soLuong = suDungDichVu.getSoLuong();
        return giaDichVu*soLuong;
    }

}