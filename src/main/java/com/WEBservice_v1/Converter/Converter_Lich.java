package com.WEBservice_v1.Converter;

import com.WEBservice_v1.DTO.DTO_Lich;
import com.WEBservice_v1.DTO.DTO_Phim;
import com.WEBservice_v1.DTO.DTO_Phong;
import com.WEBservice_v1.Entity.Lich;
import com.WEBservice_v1.Entity.Phim;
import com.WEBservice_v1.Entity.Phong;
import com.WEBservice_v1.Service.Service_Phim;
import com.WEBservice_v1.Service.Service_Phong;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Converter_Lich {
    @Autowired
    Service_Phong service_phong;
    @Autowired
    Service_Phim service_phim;
    public DTO_Lich convertLich_to_DTO(Lich lich){
        DTO_Lich dto = new DTO_Lich();
        dto.setMalich(lich.getMalich());
        dto.setGio(lich.getGio());
        dto.setMaphim(lich.getPhim().getIdphim());
        dto.setMaphong(lich.getPhong().getIdphong());
        dto.setNgay(lich.getNgay());
        return  dto;
    }
    public  Lich convertDTO_to_Lich(DTO_Lich dto){
        Lich lich = new Lich();
        lich.setMalich(dto.getMalich());
        lich.setGio(dto.getGio());
        Phim phim = new Phim();
        phim.setIdphim(dto.getMaphim());
        Phong phong = new Phong();
        phong.setIdphong(dto.getMaphong());


        lich.setPhim(phim);
       lich.setPhong(phong) ;
        lich.setNgay(dto.getNgay());

        return lich;


    }

}
