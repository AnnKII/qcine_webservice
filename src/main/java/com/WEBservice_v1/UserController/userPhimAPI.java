package com.WEBservice_v1.UserController;

import com.WEBservice_v1.DTO.DTO_Phim;
import com.WEBservice_v1.Service.Service_Phim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class userPhimAPI {
    @Autowired
    Service_Phim ser_phim;
    @GetMapping("user/phim")
    public Object getCurrentPhim(){
        try{
            List<DTO_Phim> result = (List<DTO_Phim>) ser_phim.getCurrentPhim();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("user/futurephim")
    public Object getFuturePhim(){
        try{
            List<DTO_Phim> result = (List<DTO_Phim>) ser_phim.getFuturePhim();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("user/phim/{idphim}")
    public Object getPhim(@PathVariable("idphim") String idphim){
        try{
            DTO_Phim result = new DTO_Phim();
            result = (DTO_Phim) ser_phim.getPhim(idphim);
            return new ResponseEntity<Object>(result, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<Object>(ser_phim.getPhim(idphim).toString(), HttpStatus.NO_CONTENT);
        }
    }
}
