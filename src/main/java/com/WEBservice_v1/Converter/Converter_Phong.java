package com.WEBservice_v1.Converter;

import com.WEBservice_v1.DTO.DTO_Phong;
import com.WEBservice_v1.Entity.Phong;

public class Converter_Phong {
    public DTO_Phong convertPhong_to_DTO(Phong phong){
        DTO_Phong dto = new DTO_Phong();
        dto.setIdphong(phong.getIdphong());
        dto.setSocot(phong.getSocot());
        dto.setSohang(phong.getSohang());
        dto.setTenphong(phong.getTenphong());
        dto.setTinhtrang(phong.getTinhtrang());

        return dto;
    }
    public Phong convertDTO_to_Phong(DTO_Phong dto){
        Phong phong = new Phong();
        phong.setIdphong(dto.getIdphong());
        phong.setSocot(dto.getSocot());
        phong.setSohang(dto.getSohang());
        phong.setTenphong(dto.getTenphong());
        phong.setTinhtrang(dto.getTinhtrang());

        return phong;
    }

}
