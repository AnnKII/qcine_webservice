package com.WEBservice_v1.Converter;

import com.WEBservice_v1.DTO.DTO_HoaDon;
import com.WEBservice_v1.Entity.HoaDon;
import com.WEBservice_v1.Entity.KhachHang;
import com.WEBservice_v1.Entity.Lich;

public class Converter_HoaDon {
    public DTO_HoaDon convertHoaDon_to_DTO(HoaDon hoadon){
        DTO_HoaDon dto = new DTO_HoaDon();
        dto.setMahd(hoadon.getMahd());
        dto.setMaKhachHang(hoadon.getKhachhang().getUsername());
        dto.setMalich(hoadon.getLich().getMalich());
        dto.setTglap(hoadon.getTglap());
        dto.setThanhtien(hoadon.getThanhtien());
        return dto;
    }
    public HoaDon convertDTO_to_HoaDon(DTO_HoaDon dto){
        HoaDon hoadon = new HoaDon();
        hoadon.setMahd(dto.getMahd());
        hoadon.setTglap(dto.getTglap());
        hoadon.setThanhtien(dto.getThanhtien());
        Lich lich = new Lich();
        lich.setMalich(dto.getMalich());
        hoadon.setLich(lich);
        KhachHang khachHang = new KhachHang();
        khachHang.setUsername(dto.getMaKhachHang());
        hoadon.setKhachhang(khachHang);
        return  hoadon;

    }
}
