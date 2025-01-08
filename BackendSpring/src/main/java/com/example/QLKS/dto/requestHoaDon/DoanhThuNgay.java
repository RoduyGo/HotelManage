package com.example.QLKS.dto.requestHoaDon;

public class DoanhThuNgay {
    private int year;
    private int month;
    private int day;
    private double revenue;

    public DoanhThuNgay(int year, int month, int day, double revenue) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.revenue = revenue;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
