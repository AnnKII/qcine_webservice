package com.WEBservice_v1.UserController;

import com.WEBservice_v1.DTO.DTO_Phong;
import com.WEBservice_v1.Service.Service_Phong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userPhongAPI {
    @Autowired
    Service_Phong ser_Phong;

    @GetMapping("/user/phong/{idphong}")
    public ResponseEntity<Object> getPhong(@PathVariable("idphong") String idphong){
        try{
            System.out.println("Ma phong: "+ idphong);
            DTO_Phong phong = (DTO_Phong) ser_Phong.getPhong(idphong.trim());
            return new ResponseEntity<>(phong, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
