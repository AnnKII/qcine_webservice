package com.WEBservice_v1.UserController;

import com.WEBservice_v1.DTO.DTO_Lich;
import com.WEBservice_v1.Service.Service_Lich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class userLichAPI {
    @Autowired
    Service_Lich ser_lich;

    @GetMapping("user/lich")
    public Object getCurrentLich(){
        try{
            List<DTO_Lich> result = (List<DTO_Lich>) ser_lich.getCurrentLich();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("FAILED", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("user/lich/idphim/{idphim}")
    public Object getTodayLichFollowedByPhim(@PathVariable("idphim") String idphim){
        try{
            List<DTO_Lich> result = (List<DTO_Lich>) ser_lich.getTodayLichFollowedByPhim(idphim);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("FAILED", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("user/lich/{malich}")
    public Object getLichFollowedByMaLich(@PathVariable("malich") String malich){
        try{
            DTO_Lich result = (DTO_Lich) ser_lich.getLich(Integer.parseInt(malich.trim()));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("FAILED", HttpStatus.NO_CONTENT);
        }
    }
    @PostMapping("user/lich/ngay")
    public Object getLichFollowedByNgay(@RequestBody DTO_Lich lich){
        try{
            List<DTO_Lich> result = (List<DTO_Lich>) ser_lich.getLichFollowedByDate(lich.getNgay());
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }

    };
}
