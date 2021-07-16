package com.WEBservice_v1.AdminController;

import com.WEBservice_v1.DTO.DTO_Phim;
import com.WEBservice_v1.entity_KeyINFO;
import com.WEBservice_v1.Service.Service_AuthorKey;
import com.WEBservice_v1.Service.Service_KhachHang;
import com.WEBservice_v1.Service.Service_Phim;
import com.WEBservice_v1.Verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class phimAPI {
    @Autowired
    Service_Phim ser_Phim;
    //Authorization
    @Autowired
    Service_AuthorKey ser_Author;
    static entity_KeyINFO login = new entity_KeyINFO();
    static Verify verifier = new Verify();

    @GetMapping("admin/phim/{id}")
    public ResponseEntity<Object> getPhim(@PathVariable("id") String id, @RequestHeader("author") String jwt){

        if(verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                DTO_Phim result = new DTO_Phim();
                result = (DTO_Phim) ser_Phim.getPhim(id);
                return new ResponseEntity<Object>(result, HttpStatus.OK);

            } catch (Exception e){
                return new ResponseEntity<Object>(ser_Phim.getPhim(id).toString(), HttpStatus.NOT_ACCEPTABLE);
            }
            } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);

    }

    @GetMapping("admin/phim")
    public ResponseEntity<Object> getAllPhim(@RequestHeader("author") String jwt){
        if(verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                List<DTO_Phim> listPhim = (List<DTO_Phim>) ser_Phim.getAllPhim();
                return new ResponseEntity<>(listPhim, HttpStatus.OK);
            }
            catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<Object>("EMPTY",HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("admin/currentphim")
    public ResponseEntity<Object> getCurrentPhim(@RequestHeader("author") String jwt){
        if(verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                List<DTO_Phim> listPhim = (List<DTO_Phim>) ser_Phim.getCurrentPhim();
                return new ResponseEntity<>(listPhim, HttpStatus.OK);
            }
            catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<Object>("EMPTY",HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("admin/phim")
    public ResponseEntity<Object> createPhim(@RequestBody DTO_Phim dto, @RequestHeader("author") String jwt){
        if(verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                String status = (String) ser_Phim.createPhim(dto);
                if (status.equals("SUCCESS"))
                    return new ResponseEntity<Object>(status, HttpStatus.OK);
                else return new ResponseEntity<Object>(status, HttpStatus.NOT_ACCEPTABLE);
            } catch (Exception e){
                return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
            }

        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);

    }


    @PutMapping("admin/phim")
    public ResponseEntity<Object> updatePhim(@RequestBody DTO_Phim dto, @RequestHeader("author") String jwt){
       if (verifier.verifyLogin(jwt, login, ser_Author)){
           try{
               String status = (String) ser_Phim.updatePhim(dto);
               if (status.equals("SUCCESS"))
                   return new ResponseEntity<Object>(status, HttpStatus.OK);
               else return new ResponseEntity<Object>(status, HttpStatus.NOT_ACCEPTABLE);
           } catch (Exception e){
               return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
           }
       } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping(value = "admin/phim/{id}")
    public ResponseEntity<Object> deletePhim(@PathVariable String id, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try {
                String status = (String) ser_Phim.deletePhim(id);
                if(status.equals("SUCCESS"))
                    return new ResponseEntity<Object>(status, HttpStatus.OK);
                else return new ResponseEntity<Object>(status, HttpStatus.NOT_ACCEPTABLE);
            } catch (Exception e){
                return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }


}
