package com.WEBservice_v1.Service;

import com.WEBservice_v1.Converter.Converter_Phim;
import com.WEBservice_v1.Converter.Converter_Phong;
import com.WEBservice_v1.DTO.DTO_Phong;
import com.WEBservice_v1.Entity.Phong;
import com.WEBservice_v1.Repository.Repository_Phong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Service_Phong {
    @Autowired
    Repository_Phong res_Phong;

    public Object getAllPhong(){
        List<Phong> phongs = new ArrayList<>();
        phongs = res_Phong.findAll();
        if (phongs.isEmpty()){
            return "EMPTY";
        }
        else {
            Converter_Phong converter_phong = new Converter_Phong();
            List<DTO_Phong> result = new ArrayList<>();
            for (int i=0; i< phongs.size(); i++)
                result.add(converter_phong.convertPhong_to_DTO(phongs.get(i)));
            return result;
        }
    }

    public Object getPhong(String idphong){
        if (res_Phong.getNumberPhong(idphong)!=0){
            Phong phong = res_Phong.getPhong(idphong);
            Converter_Phong converter = new Converter_Phong();
            return converter.convertPhong_to_DTO(phong);
        }
        else return "NOT FOUND";
    }
    public Object getActivePhong(){
        List<Phong> listPhong = res_Phong.getActivePhong();
        if(!listPhong.isEmpty()){
            List<DTO_Phong> result = new ArrayList<>();
            Converter_Phong converter = new Converter_Phong();
            for(Phong temp: listPhong){
                result.add(converter.convertPhong_to_DTO(temp));
            }
            return result;
        }else return "EMPTY";
    }

    public Object UpdatePhong(DTO_Phong dto){
        if (res_Phong.getNumberPhong(dto.getIdphong())!=0){
            Converter_Phong converter = new Converter_Phong();
            try{
                res_Phong.save(converter.convertDTO_to_Phong(dto));
                return "SUCCESS";
            }
            catch (Exception e){
                return "FAILED";
            }
        }
        else return "NOT FOUND";
    }
    public  Object deletePhong(String id){
        if (res_Phong.getNumberPhong(id)!=0){
            try {
                res_Phong.delete(id);
                return "SUCCESS";

            }
            catch (Exception e){
                return "FAILED";
            }
        }
        else return "NOT FOUND";
    }
    public Object createPhong(DTO_Phong dto){
        if (res_Phong.getNumberPhong(dto.getIdphong())!=0)
            return "TAKEN";
        else {
            Converter_Phong converter = new Converter_Phong();
            try {
                res_Phong.save(converter.convertDTO_to_Phong(dto));
                return "SUCCESS";
            }
            catch (Exception e){
                return "FAILED";
            }
        }
    }
    public Object getPhongFollowedByLichDateAndTime(Date ngay, Time gio){
        List<Phong> listPhong = new ArrayList<>();
        try{
            listPhong = (List<Phong>) res_Phong.getUsablePhong(ngay, gio);
            if(!listPhong.isEmpty()){
                Converter_Phong converter = new Converter_Phong();
                List<DTO_Phong> listDTO = new ArrayList<>();
                for (Phong phong: listPhong){
                    listDTO.add(converter.convertPhong_to_DTO(phong));
                }
                return listDTO;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return "EMPTY";
    }


}
