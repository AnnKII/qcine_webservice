package com.WEBservice_v1.DTO;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class DTO_Lich {
    int malich;
    Date ngay;
    Time gio;
    String maphong;
    String maphim;

    public int getMalich() {
        return malich;
    }

    public void setMalich(int malich) {
        this.malich = malich;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Time getGio() {
        return gio;
    }

    public void setGio(Time gio) {
        this.gio = gio;
    }

    public String getMaphong() {
        return maphong;
    }

    public void setMaphong(String maphong) {
        this.maphong = maphong;
    }

    public String getMaphim() {
        return maphim;
    }

    public void setMaphim(String maphim) {
        this.maphim = maphim;
    }
}
