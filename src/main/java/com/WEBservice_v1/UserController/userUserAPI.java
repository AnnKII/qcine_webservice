package com.WEBservice_v1.UserController;

import com.WEBservice_v1.DTO.DTO_KhachHang;
import com.WEBservice_v1.Service.Service_KhachHang;
import com.WEBservice_v1.entity_LoginINFO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class userUserAPI {
    @Autowired
    Service_KhachHang ser_khach;

    @PostMapping("user/profile")
    public ResponseEntity<Object> getUserINFO(@RequestBody entity_LoginINFO info){
        try{
            System.out.println("Username: "+info.getUsername());
            DTO_KhachHang temp = (DTO_KhachHang) ser_khach.getKhachHang(info.getUsername().trim());
            temp.setPassword("");
            return  new ResponseEntity<>(temp, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
