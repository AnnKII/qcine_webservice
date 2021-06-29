package com.WEBservice_v1.DTO;

import java.util.Date;

public class DTO_KhachHang {
    String username;
    String password;
    String diachi;
    String ho;
    String ten;
    Date ngaysinh;
    Date ngaygianhap;
    String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public Date getNgaygianhap() {
        return ngaygianhap;
    }

    public void setNgaygianhap(Date ngaygianhap) {
        this.ngaygianhap = ngaygianhap;
    }
}
