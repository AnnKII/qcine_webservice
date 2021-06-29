package com.WEBservice_v1.Service;

import com.WEBservice_v1.Converter.Converter_CtHoaDon;
import com.WEBservice_v1.Converter.Converter_Lich;
import com.WEBservice_v1.DTO.DTO_CtHoaDon;
import com.WEBservice_v1.DTO.DTO_Lich;
import com.WEBservice_v1.Entity.CtHoaDon;
import com.WEBservice_v1.Entity.Lich;
import com.WEBservice_v1.Repository.Repository_Lich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Service_Lich {
    @Autowired
    Repository_Lich res_Lich;

    public Object getAllLich(){
        List<Lich> listLich = new ArrayList<>();
        listLich = res_Lich.findAll();
        if(listLich.isEmpty())
            return "EMPTY";
        else {
            List<DTO_Lich> result = new ArrayList<>();
            Converter_Lich converter = new Converter_Lich();
            for (int i=0; i< listLich.size(); i++){
                result.add(converter.convertLich_to_DTO(listLich.get(i)));
            }
            return  result;
        }
    }

    public Object getLich(int malich){
        if (res_Lich.getNumberLich(malich)!=0){
            Converter_Lich converter_lich = new Converter_Lich();
            return  converter_lich.convertLich_to_DTO(res_Lich.getLich(malich));
        }
        else return "NOT FOUND";
    }

    public Object createLich(DTO_Lich dto){
        if (dto == null)
            return "NULL";
        else {
            Converter_Lich converter = new Converter_Lich();
            try{
                Lich lich = new Lich();
                lich = converter.convertDTO_to_Lich(dto);
                res_Lich.save(lich);
                return "SUCCESS";
            }
            catch (Exception e){
                e.printStackTrace();
                return e.toString();
            }
        }

    }
    public Object updateLich(DTO_Lich dto){
        if(dto == null)
            return "NULL";
        else {
            if (res_Lich.getNumberLich(dto.getMalich())==0)
                return "NOT FOUND";
            else {
                Converter_Lich converter = new Converter_Lich();

                try{
                    res_Lich.save(converter.convertDTO_to_Lich(dto));
                    return "SUCCESS";
                }
                catch (Exception e){
                    e.printStackTrace();
                    return e.toString();
                }

            }
        }
    }
    public  Object deleteLich(int malich){
        if(res_Lich.getNumberLich(malich)!=0){
            try {
                res_Lich.delete(malich);
                return "SUCCESS";
            }
            catch (Exception e){
                return "FAILED";
            }
        }
        else  return "NOT FOUND";
    }

    public Object getCurrentLich(){
        try{
            List<Lich> listLich = res_Lich.getCurrentLich();
            if(!listLich.isEmpty()){
                Converter_Lich converter = new Converter_Lich();
                List<DTO_Lich> result = new ArrayList<>();
                for(int i=0; i<listLich.size(); i++){
                    result.add(converter.convertLich_to_DTO(listLich.get(i)));
                }
                return result;
            }
            else return "FAILED";
        } catch (Exception e){
            e.printStackTrace();
            return "FAILED";
        }
    }

    public Object getLichFollowedByPhim(String idphim){
        try{
            List<Lich> listLich = res_Lich.getLichFollowedByPhim(idphim);
            if(!listLich.isEmpty()){
                Converter_Lich converter = new Converter_Lich();
                List<DTO_Lich> result = new ArrayList<>();
                for(int i=0; i<listLich.size(); i++){
                    result.add(converter.convertLich_to_DTO(listLich.get(i)));
                }
                return result;
            }
            else return "FAILED";
        } catch (Exception e){
            e.printStackTrace();
            return "FAILED";
        }
    }

    public Object getTodayLichFollowedByPhim(String idphim){
        try{
            List<Lich> listLich = res_Lich.getTodayLichFollowedByPhim(idphim);
            if(!listLich.isEmpty()){
                Converter_Lich converter = new Converter_Lich();
                List<DTO_Lich> result = new ArrayList<>();
                for(int i=0; i<listLich.size(); i++){
                    result.add(converter.convertLich_to_DTO(listLich.get(i)));
                }
                return result;
            }
            else return "FAILED";
        } catch (Exception e){
            e.printStackTrace();
            return "FAILED";
        }
    }
    public Object getLichFollowedByDate(Date date){
        try{
            List<Lich> listLich = res_Lich.getLichFollowedByNgay(date);
            if(!listLich.isEmpty()){
                Converter_Lich converter = new Converter_Lich();
                List<DTO_Lich> result = new ArrayList<>();
                for (Lich temp: listLich){
                    result.add(converter.convertLich_to_DTO(temp));
                }
                return result;
            }
            return "FAILED";
        } catch (Exception e){
            e.printStackTrace();
            return "FAILED";
        }
    }
    public Object getLichFollowedByDateAndPhim(Date date, String idphim){
        try{
            List<Lich> listLich = res_Lich.getLichFollowedByDateAndPhim(date, idphim);
            if (!listLich.isEmpty()){
                List<DTO_Lich> listDTO = new ArrayList<>();
                Converter_Lich converter = new Converter_Lich();
                for(Lich temp:listLich){
                    listDTO.add(converter.convertLich_to_DTO(temp));
                }
                return listDTO;
            }
            else return "FAILED";
        }catch (Exception e){
            e.printStackTrace();
            return "FAILED";
        }
    }
}
