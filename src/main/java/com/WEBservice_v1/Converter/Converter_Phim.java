package com.WEBservice_v1.Converter;

import com.WEBservice_v1.DTO.DTO_Phim;
import com.WEBservice_v1.Entity.Phim;

public class Converter_Phim {


    public Phim covertDTO_to_Phim(DTO_Phim dto){
        Phim phim = new Phim();
        phim.setAnh(dto.getAnh());
        phim.setIdphim(dto.getIdphim());
        phim.setMota(dto.getMota());
        phim.setNgaykc(dto.getNgaykc());
        phim.setTenphim(dto.getTenphim());
        phim.setThoiluong(dto.getThoiluong());
        phim.setTrailer(dto.getTrailer());
        phim.setState(dto.getState());
        return phim;
    }
    public DTO_Phim convertPhim_to_DTO(Phim phim){
        DTO_Phim dto = new DTO_Phim();
        dto.setAnh(phim.getAnh());
        dto.setIdphim(phim.getIdphim());
        dto.setMota(phim.getMota());
        dto.setNgaykc(phim.getNgaykc());
        dto.setTenphim(phim.getTenphim());
        dto.setThoiluong(phim.getThoiluong());
        dto.setTrailer(phim.getTrailer());
        dto.setState(phim.getState());
        return dto;
    }

}
