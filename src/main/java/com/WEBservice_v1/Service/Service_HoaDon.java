package com.WEBservice_v1.Service;

import com.WEBservice_v1.Converter.Converter_HoaDon;
import com.WEBservice_v1.DTO.DTO_HoaDon;
import com.WEBservice_v1.DTO.DTO_Lich;
import com.WEBservice_v1.Entity.HoaDon;
import com.WEBservice_v1.Repository.Repository_HoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Service_HoaDon {

    @Autowired
    Repository_HoaDon res_HD;

    public Object getAllHD(){
        List<HoaDon> listHD = new ArrayList<>();
        listHD = res_HD.findAll();
        if (listHD.isEmpty())
            return "EMPTY";
        else {
            Converter_HoaDon converter = new Converter_HoaDon();
            List<DTO_HoaDon> result = new ArrayList<>();
            for (int i=0; i<listHD.size(); i++)
                result.add(converter.convertHoaDon_to_DTO(listHD.get(i)));
            return result;
        }
    }
    public Object getHD(int mahd){
        if(res_HD.getNumberHoaDon(mahd)==0)
            return "NOT FOUND";
        else {
            Converter_HoaDon converter = new Converter_HoaDon();
            return converter.convertHoaDon_to_DTO(res_HD.getHoaDon(mahd));
        }
    }

    public Object createHD(DTO_HoaDon dto){
        Converter_HoaDon converter = new Converter_HoaDon();
        try {
            res_HD.save(converter.convertDTO_to_HoaDon(dto));
            return "SUCCESS";
        }
        catch (Exception e){
            return "FAILED";
        }
    }

    public Object updateHD(DTO_HoaDon dto){
        if (res_HD.getNumberHoaDon(dto.getMahd())==0)
            return "NOT FOUND";
        else {
            Converter_HoaDon converter = new Converter_HoaDon();
            try {
                res_HD.save(converter.convertDTO_to_HoaDon(dto));
                return  "SUCCESS";
            }
            catch (Exception e){
                return "FAILED";
            }
        }

    }
    public Object deleteHD(int mahd){
        if (res_HD.getNumberHoaDon(mahd)==0)
            return "NOT FOUND";
        else {
            try {
                res_HD.delete(mahd);
                return "SUCCESS";
            }
            catch (Exception e){
                return "FAILED";
            }
        }
    }
    public Object getHhoaDonByUser(String username){
        try {
            List<HoaDon> listHD = res_HD.getHoaDonByUser(username);
            if (!listHD.isEmpty()){
                List<DTO_HoaDon> result = new ArrayList<>();
                Converter_HoaDon converter = new Converter_HoaDon();
                for (HoaDon hd: listHD){
                    result.add(converter.convertHoaDon_to_DTO(hd));
                }
                return result;
            }
            else return "FAIL";
        } catch (Exception e){
            System.out.println("(Service_HoaDon ERR) DON'T HAVE HOADON FOLLOWED BY USER");
            return "FAIL";
        }
    }
    public int getMaHDCreate(int malich, String username, Date tglap, int thanhTien){
        try{
            List<Integer> listHD = res_HD.getMaHDCreated(malich, username, tglap, thanhTien);
            int result = listHD.get(listHD.size()-1);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    public Object getHoaDonFollowedByKhacHang(String username){
        try{
            List<HoaDon> listHD = res_HD.getHoaDonByUser(username);
            if(!listHD.isEmpty()){
                List<DTO_HoaDon> resultList = new ArrayList<>();
                Converter_HoaDon converter = new Converter_HoaDon();
                for(HoaDon temp: listHD){
                    resultList.add(converter.convertHoaDon_to_DTO(temp));
                }
                return resultList;
            }
        }catch (Exception e){
            e.printStackTrace();
            return "FAILED";
        }
        return "FAILED";
    }


}
