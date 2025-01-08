package com.example.QLKS.dto.requestHoaDon;

public class BCPhong {
    private String roomType;
    private int count;

    public BCPhong(String roomType, int count) {
        this.roomType = roomType;
        this.count = count;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
