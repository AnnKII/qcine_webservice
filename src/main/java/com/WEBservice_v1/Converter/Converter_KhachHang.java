package com.WEBservice_v1.Converter;

import com.WEBservice_v1.DTO.DTO_KhachHang;
import com.WEBservice_v1.Entity.KhachHang;

public class Converter_KhachHang {
    public KhachHang convertDTO_to_KhachHang(DTO_KhachHang dto){
        KhachHang khach = new KhachHang();

        khach.setRole(dto.getRole());
        khach.setUsername(dto.getUsername());
        khach.setHo(dto.getHo());
        khach.setTen(dto.getTen());
        khach.setDiachi(dto.getDiachi());
        khach.setNgaysinh(dto.getNgaysinh());
        khach.setNgaygianhap(dto.getNgaygianhap());
        khach.setPass(dto.getPassword());
        return khach;
    }

    public DTO_KhachHang convertKhachHang_to_DTO(KhachHang khach){
        DTO_KhachHang dto = new DTO_KhachHang();


        dto.setUsername(khach.getUsername());
        dto.setHo(khach.getHo());
        dto.setTen(khach.getTen());
        dto.setDiachi(khach.getDiachi());
        dto.setNgaysinh(khach.getNgaysinh());
        dto.setNgaygianhap(khach.getNgaygianhap());
        dto.setPassword(khach.getPass());
        dto.setRole(khach.getRole());
        return dto;
    }
}
