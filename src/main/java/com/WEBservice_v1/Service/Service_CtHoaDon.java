package com.WEBservice_v1.Service;

import com.WEBservice_v1.Converter.Converter_CtHoaDon;
import com.WEBservice_v1.DTO.DTO_CtHoaDon;
import com.WEBservice_v1.Entity.CtHoaDon;
import com.WEBservice_v1.Repository.Repository_CtHoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Service_CtHoaDon {
    @Autowired
    Repository_CtHoaDon res_CT;
    public Object getAllCtHoaDon(){
        List<CtHoaDon> listDTO = new ArrayList<>();
        listDTO = res_CT.findAll();
        if(listDTO.isEmpty())
            return "EMPTY";
        else {
            List<DTO_CtHoaDon> result = new ArrayList<>();
            Converter_CtHoaDon converter = new Converter_CtHoaDon();
            for (int i = 0; i <listDTO.size() ; i++) {
                result.add(converter.convertCtHoaDon_to_DTO(listDTO.get(i)));
            }
            return result;
        }

    }

    public Object getCtHoaDon(int mact){
        if(res_CT.getNumberCt(mact)==0)
            return "NOT FOUND";
        else {
            Converter_CtHoaDon converter = new Converter_CtHoaDon();
            return converter.convertCtHoaDon_to_DTO(res_CT.getCT(mact));
        }
    }

    public Object createCtHoaDon(DTO_CtHoaDon dto){
        if(res_CT.getNumberCt(dto.getMact())!=0)
            return "TAKEN";
        else {
            Converter_CtHoaDon converter = new Converter_CtHoaDon();
            try {
                res_CT.save(converter.convertDTO_to_CtHoaDon(dto));
                return "SUCCESS";
            }
            catch (Exception e){
                return "FAILED";
            }
        }
    }
    public Object updateCtHoaDon(DTO_CtHoaDon dto){
        if(res_CT.getNumberCt(dto.getMact())==0)
            return "NOT FOUND";
        else {
            Converter_CtHoaDon converter = new Converter_CtHoaDon();
            try {
                res_CT.save(converter.convertDTO_to_CtHoaDon(dto));
                return "SUCCESS";
            }
            catch (Exception e){
                return "FAILED";
            }
        }
    }
    public Object deleteCtHoaDon(int mact){
        if (res_CT.getNumberCt(mact)==0)
            return "NOT FOUND";
        else {
            try {
                res_CT.delete(mact);
                return "SUCCESS";
            }
            catch (Exception e){
                return "FAILED";
            }
        }
    }
    public Object getCtHoaDonByMaHD(int mahd){
        try{
            List<CtHoaDon> listCT = new ArrayList<>();
            listCT = res_CT.getCtHoaDonByMaHD(mahd);
            if (!listCT.isEmpty()){
                Converter_CtHoaDon converter = new Converter_CtHoaDon();
                List<DTO_CtHoaDon> result = new ArrayList<>();
                for (CtHoaDon ct: listCT){
                    result.add(converter.convertCtHoaDon_to_DTO(ct));
                }
                return result;
            } return "FAIL";

        } catch (Exception e){
            return "FAIL";
        }
    }
    public Object getCtHoaDonFollowedByMaLich(int malich){
        try{
            List<CtHoaDon> listCT = res_CT.getCtHoaDonFollowedByLich(malich);
            if(!listCT.isEmpty()){
                List<DTO_CtHoaDon> listDTO = new ArrayList<>();
                Converter_CtHoaDon converter = new Converter_CtHoaDon();
                for(CtHoaDon temp : listCT){
                    listDTO.add(converter.convertCtHoaDon_to_DTO(temp));
                }
                return listDTO;
            }
        }catch (Exception e){
            e.printStackTrace();
            return  "FAILED";
        }
        return "FAILED";
    }
}
