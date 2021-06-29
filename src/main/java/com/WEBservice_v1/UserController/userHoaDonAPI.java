package com.WEBservice_v1.UserController;

import com.WEBservice_v1.DTO.DTO_HoaDon;
import com.WEBservice_v1.Service.Service_HoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class userHoaDonAPI {
    @Autowired
    Service_HoaDon ser_HoaDon;
    @GetMapping("user/hoadon/username={username}")
    public Object getHoaDonByUser(@PathVariable("username") String username){
        try{
            List<DTO_HoaDon> result = new ArrayList<>();
            result = (List<DTO_HoaDon>) ser_HoaDon.getHhoaDonByUser(username);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("user/hoadon/mahd={mahd}")
    public Object getHoaDonByMaHD(@PathVariable("mahd") int mahd){
        try{
            DTO_HoaDon result = new DTO_HoaDon();
            result = (DTO_HoaDon) ser_HoaDon.getHD(mahd);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
        }
    }
}
