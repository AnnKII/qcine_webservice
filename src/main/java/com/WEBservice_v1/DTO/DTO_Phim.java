package com.WEBservice_v1.DTO;

import java.util.Date;

public class DTO_Phim {
    String idphim;
    String tenphim;
    String mota;
    Date ngaykc;
    String anh;
    String trailer;
    int thoiluong;
    int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getIdphim() {
        return idphim;
    }

    public void setIdphim(String idphim) {
        this.idphim = idphim;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Date getNgaykc() {
        return ngaykc;
    }

    public void setNgaykc(Date ngaykc) {
        this.ngaykc = ngaykc;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public int getThoiluong() {
        return thoiluong;
    }

    public void setThoiluong(int thoiluong) {
        this.thoiluong = thoiluong;
    }
}
