package com.example.QLKS.TrangThai;

public enum TrangThaiPhong {
    con_trong("Còn trống"),
    da_dat("Đã đặt"),
    dang_don("Bảo trì");

    private final String displayValue;

    TrangThaiPhong(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
