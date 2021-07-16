package com.WEBservice_v1.UserController;

import com.WEBservice_v1.DTO.DTO_CtHoaDon;
import com.WEBservice_v1.Service.Service_CtHoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class userCtHoaDonAPI {
    @Autowired
    Service_CtHoaDon ser_CT;

    @GetMapping("user/{mact}")
    public ResponseEntity<Object> getCtHoaDon(@PathVariable("mact") int mahd){
        try{
            List<DTO_CtHoaDon> result = (List<DTO_CtHoaDon>) ser_CT.getCtHoaDonByMaHD(mahd);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("user/cthoadon/lich/{malich}")
    public ResponseEntity<Object> getSeat(@PathVariable("malich") int malich){
        try{
            List<DTO_CtHoaDon> seat = (List<DTO_CtHoaDon>) ser_CT.getCtHoaDonFollowedByMaLich(malich);
            return new ResponseEntity<>(seat, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("user/cthoadon/hoadon/{mahd}")
    public ResponseEntity<Object> getCtHoaDonFollowedByMaHD(@PathVariable("mahd") int mahd){
        try{
            List<DTO_CtHoaDon> listCT = (List<DTO_CtHoaDon>) ser_CT.getCtHoaDonByMaHD(mahd);
            return new ResponseEntity<>(listCT, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


}
