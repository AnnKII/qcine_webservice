package com.WEBservice_v1.AdminController;

import com.WEBservice_v1.DTO.DTO_CtHoaDon;
import com.WEBservice_v1.Service.Service_AuthorKey;
import com.WEBservice_v1.Service.Service_CtHoaDon;
import com.WEBservice_v1.Verify;
import com.WEBservice_v1.entity_KeyINFO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ctHoaDonAPI {
    @Autowired
    Service_CtHoaDon ser_CT;
    //Authorization
    @Autowired
    Service_AuthorKey ser_Author;
    static entity_KeyINFO login = new entity_KeyINFO();
    static Verify verifier = new Verify();

    @GetMapping("admin/cthoadon")
    public ResponseEntity<Object> getAllCtHoaDon(@RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                List<DTO_CtHoaDon> reuslt = (List<DTO_CtHoaDon>) ser_CT.getAllCtHoaDon();
                return new ResponseEntity<Object>(reuslt, HttpStatus.OK);
            }
            catch (Exception e){
                return new ResponseEntity<Object>("EMPTY", HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("admin/cthoadon/{mact}")
    public ResponseEntity<Object> getCtHoaDon(@PathVariable("mact") int mact, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                DTO_CtHoaDon ct = (DTO_CtHoaDon) ser_CT.getCtHoaDon(mact);
                return new ResponseEntity<Object>(ct, HttpStatus.OK);
            }
            catch (Exception e){
                return new ResponseEntity<Object>("NOT FOUND", HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("admin/cthoadon")
    public ResponseEntity<Object> createCtHoaDon(@RequestBody DTO_CtHoaDon dto, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                String status = (String) ser_CT.createCtHoaDon(dto);
                if (status.equals("SUCCESS"))
                    return new ResponseEntity<Object>("SUCCESS", HttpStatus.OK);
                else return new ResponseEntity<>(status, HttpStatus.NOT_ACCEPTABLE);
            } catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("admin/cthoadon")
    public ResponseEntity<Object> updateCtHoaDon(@RequestBody DTO_CtHoaDon dto, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                String status = (String) ser_CT.updateCtHoaDon(dto);
                if (status.equals("SUCCESS"))
                    return new ResponseEntity<Object>("SUCCESS", HttpStatus.OK);
                else return new ResponseEntity<>(status, HttpStatus.NOT_ACCEPTABLE);
            } catch (Exception e){

                return new ResponseEntity<>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("admin/cthoadon/{mact}")
    public ResponseEntity<Object> deleteCtHoaDon(@PathVariable("mact") int mact, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                String status = (String) ser_CT.deleteCtHoaDon(mact);
                if (status.equals("SUCCESS"))
                    return new ResponseEntity<Object>("SUCCESS", HttpStatus.OK);
                else return new ResponseEntity<>(status, HttpStatus.NOT_ACCEPTABLE);
            } catch (Exception e ){
                return new ResponseEntity<>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("admin/cthoadon/lich/{malich}")
    public ResponseEntity<Object> getCtHoaDonFollowedByMaLich(@RequestHeader("author") String jwt,@PathVariable("malich") int malich){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                List<DTO_CtHoaDon> reuslt = (List<DTO_CtHoaDon>) ser_CT.getCtHoaDonFollowedByMaLich(malich);
                if(!reuslt.isEmpty()){
                    return new ResponseEntity<Object>(reuslt, HttpStatus.OK);
                } else {return new ResponseEntity<Object>("EMPTY", HttpStatus.NO_CONTENT);}
            }
            catch (Exception e){
                return new ResponseEntity<Object>("EMPTY", HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }
}
