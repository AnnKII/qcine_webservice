package com.WEBservice_v1.Service;

import com.WEBservice_v1.Converter.Converter_Phim;
import com.WEBservice_v1.Converter.Converter_Phong;
import com.WEBservice_v1.DTO.DTO_Phim;
import com.WEBservice_v1.Entity.Phim;
import com.WEBservice_v1.Repository.Repository_Phim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Service_Phim {
    @Autowired
    Repository_Phim res_Phim;

    public Object getAllPhim(){
        List<Phim> result;
        result = res_Phim.findAll();
        if(!result.isEmpty()){
            List<DTO_Phim> rs = new ArrayList<>();
            Converter_Phim converter = new Converter_Phim();
            for (int i=0; i<result.size(); i++){
                rs.add(converter.convertPhim_to_DTO(result.get(i)));
            }
            return rs;
        }

        else {

            return "EMPTY";
        }
    }


    public Object createPhim(DTO_Phim dto){
        if (res_Phim.getNumberPhim(dto.getIdphim())!=0)
            return "TAKEN";
        else {
            Converter_Phim converter = new Converter_Phim();
            try{
                res_Phim.save(converter.covertDTO_to_Phim(dto));
                return "SUCCESS";
            }
            catch (Exception e){
                return "FAILED";
            }

        }
    }
    public Object deletePhim(String idphim){
        if(res_Phim.getNumberPhim(idphim)!=0){
            try{
                res_Phim.delete(idphim);
                return "SUCCESS";
            }
            catch (Exception e){
                return "FAILED";
            }
        }
        else return "NOT FOUND";
    }
    public Object getPhim(String idphim){
        if (res_Phim.getNumberPhim(idphim)!=0){
            Converter_Phim converter = new Converter_Phim();
            return converter.convertPhim_to_DTO(res_Phim.getPhim(idphim));
        }
        else return "NOT FOUND";
    }
    public Object updatePhim(DTO_Phim dto){
        if (res_Phim.getNumberPhim(dto.getIdphim())!=0){
            try {
                Converter_Phim converter = new Converter_Phim();
                res_Phim.save(converter.covertDTO_to_Phim(dto));
                return "SUCCESS";
            }
            catch (Exception e){

                return "FAILED";
            }
        }
        else return "NOT FOUND";
    }

    public Object getCurrentPhim(){
        try{
            List<Phim> listPhim = res_Phim.getCurrentPhim();
            if (!listPhim.isEmpty()){
                List<DTO_Phim> result = new ArrayList<>();
                Converter_Phim converter = new Converter_Phim();
                for (int i=0; i<listPhim.size(); i++){
                    result.add(converter.convertPhim_to_DTO(listPhim.get(i)));
                 //   System.out.println(listPhim.get(i).getTenphim());
                }
                return result;
            } else return "FAILED";
        } catch (Exception e){
            return "FAILED";
        }

    }

    public Object getFuturePhim(){
        try{
            List<Phim> listPhim = res_Phim.getFuturePhim();
            if (!listPhim.isEmpty()){
                List<DTO_Phim> result = new ArrayList<>();
                Converter_Phim converter = new Converter_Phim();
                for (int i=0; i<listPhim.size(); i++){
                    result.add(converter.convertPhim_to_DTO(listPhim.get(i)));
            //        System.out.println(listPhim.get(i).getTenphim());
                }
                return result;
            } else return "FAILED";
        } catch (Exception e){
            return "FAILED";
        }
    }
}
