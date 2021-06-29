package com.WEBservice_v1.DTO;

import java.util.Date;

public class DTO_HoaDon {
    int mahd;
    Date tglap;
    int thanhtien;
    String maKhachHang;
    int malich;

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public Date getTglap() {
        return tglap;
    }

    public void setTglap(Date tglap) {
        this.tglap = tglap;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getMalich() {
        return malich;
    }

    public void setMalich(int malich) {
        this.malich = malich;
    }
}
