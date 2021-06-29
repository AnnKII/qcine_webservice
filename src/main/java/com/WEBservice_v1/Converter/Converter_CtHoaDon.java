package com.WEBservice_v1.Converter;

import com.WEBservice_v1.DTO.DTO_CtHoaDon;
import com.WEBservice_v1.Entity.CtHoaDon;
import com.WEBservice_v1.Entity.HoaDon;

public class Converter_CtHoaDon {
    public DTO_CtHoaDon convertCtHoaDon_to_DTO(CtHoaDon ct){
        DTO_CtHoaDon dto = new DTO_CtHoaDon();
        dto.setMact(ct.getMact());
        dto.setGia(ct.getGia());
        dto.setMahd(ct.getHoadon().getMahd());
        dto.setHang(ct.getHang());
        dto.setCot(ct.getCot());
        return dto;
    }
    public CtHoaDon convertDTO_to_CtHoaDon(DTO_CtHoaDon dto){
        CtHoaDon ct = new CtHoaDon();
        ct.setMact(dto.getMact());
        ct.setGia(dto.getGia());
        ct.setHang(dto.getHang());
        ct.setCot(dto.getCot());
        HoaDon hd = new HoaDon();
        hd.setMahd(dto.getMahd());
        ct.setHoadon(hd);
        return ct;
    }
}
