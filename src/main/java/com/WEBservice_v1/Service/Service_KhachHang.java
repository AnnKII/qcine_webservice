package com.WEBservice_v1.Service;

import com.WEBservice_v1.Converter.Converter_KhachHang;
import com.WEBservice_v1.DTO.DTO_KhachHang;
import com.WEBservice_v1.Entity.KhachHang;
import com.WEBservice_v1.Repository.Repository_KhachHang;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class Service_KhachHang {
    @Autowired
    Repository_KhachHang res_Khach;

    public Object getAllKhachHang(){
        List<KhachHang> khachhangs = new ArrayList<>();
        khachhangs = res_Khach.findAll();
        if (khachhangs.isEmpty()){
            return "EMPTY";
        }
        else {
            List<DTO_KhachHang> result = new ArrayList<>();
            Converter_KhachHang converter = new Converter_KhachHang();
            for (int i=0; i<khachhangs.size(); i++)
                result.add(converter.convertKhachHang_to_DTO(khachhangs.get(i)));
            return result;
        }

    }

    public Object getKhachHang(String id){
        if (res_Khach.getNumberKhach(id)!=0){
            KhachHang khach = new KhachHang();
            khach = res_Khach.getKhachHang(id);
            Converter_KhachHang converter = new Converter_KhachHang();
            DTO_KhachHang dto = converter.convertKhachHang_to_DTO(khach);
            return  dto;
        }
        else return "NOT FOUND";

    }
    public Object addKhachHang(DTO_KhachHang dto){

        if (dto==null)
            return "EMPTY";
        else {

            Converter_KhachHang converter = new Converter_KhachHang();
            KhachHang khach = converter.convertDTO_to_KhachHang(dto);
            if (res_Khach.getNumberKhach(khach.getUsername())!=0)
                return "TAKEN";
            else {
                try{
                    res_Khach.save(khach);

                    return "SUCCESS";
                }
                catch (Exception e){
                    return "FAILED";
                }

            }
        }

    }
    public Object updateKhachHang(DTO_KhachHang dto){
        if (res_Khach.getNumberKhach(dto.getUsername())==0)
            return "NOT FOUND";
        else {
            Converter_KhachHang converter = new Converter_KhachHang();
            KhachHang khach = converter.convertDTO_to_KhachHang(dto);
            try {
                res_Khach.save(khach);
                return "SUCCESS";
            }
            catch (Exception e){
                return "FAILED";
            }
        }

    }
    public Object deleteKhachHang(String username){
        if (username ==null)
            return "NULL Username";
        else if (res_Khach.getNumberKhach(username)==0)
            return "NOT EXIST";
        else {
            try{
                res_Khach.delete(username);
                return "SUCCESS";
            }
            catch (Exception e){
                return "FAILED";
            }
        }
    }

    public Object login(String username, String pass){
        try{
            Converter_KhachHang converter = new Converter_KhachHang();
            DTO_KhachHang dto = converter.convertKhachHang_to_DTO(res_Khach.login(username,pass));
            return dto;
        } catch (Exception e){
            System.out.println("(Service_KhachHang ERR) NULL KhacHang DTO (can't get DTO_KhachHang)");
            e.printStackTrace();
            return "FAIL";
        }
    }
    public Object getUserWithSpecificRole(String role){
        try{
            List<KhachHang> listKH = res_Khach.getUser(role);
            if (!listKH.isEmpty()){
                try{
                    List<DTO_KhachHang> listDTO = new ArrayList<>();
                    Converter_KhachHang converter = new Converter_KhachHang();
                    for(KhachHang temp : listKH){
                        listDTO.add(converter.convertKhachHang_to_DTO(temp));

                    }
                    return  listDTO;
                }
                catch (Exception e){
                    System.out.println("Can't Fetch User list");
                    e.printStackTrace();
                    return "FAILED";
                }

            }else {
                System.out.println("Don't have any");
                return "FAILED";
            }
        } catch (Exception e){
            e.printStackTrace();
            return "FAILED";
        }
    }
    public int getCountUsername(String username){
        return res_Khach.getNumberKhach(username);
    }
}
