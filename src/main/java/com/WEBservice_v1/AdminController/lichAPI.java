package com.WEBservice_v1.AdminController;

import com.WEBservice_v1.DTO.DTO_Lich;
import com.WEBservice_v1.Service.Service_AuthorKey;
import com.WEBservice_v1.Service.Service_Lich;
import com.WEBservice_v1.Verify;
import com.WEBservice_v1.entity_KeyINFO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class lichAPI {
    @Autowired
    Service_Lich ser_Lich;
    //Authorization
    @Autowired
    Service_AuthorKey ser_Author;
    static entity_KeyINFO login = new entity_KeyINFO();
    static Verify verifier = new Verify();


    @GetMapping("admin/lich")
    public ResponseEntity<Object> getAllLich(@RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            System.out.println("Admin has entered ");
            try{
                List<DTO_Lich> result = new ArrayList<>();
                result = (List<DTO_Lich>) ser_Lich.getAllLich();
                return new ResponseEntity<Object>(result, HttpStatus.OK);
            } catch (Exception e){
                try {
                    String rs = (String) ser_Lich.getAllLich();
                    return new ResponseEntity<Object>(rs, HttpStatus.NO_CONTENT);
                } catch (Exception ex ){
                    return new ResponseEntity<Object>(e.toString(),HttpStatus.NO_CONTENT);
                }
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("admin/lich/{malich}")
    public ResponseEntity<Object> getLich(@PathVariable("malich") int malich, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                DTO_Lich result = (DTO_Lich) ser_Lich.getLich(malich);

                return new ResponseEntity<Object>(result, HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<Object>(ser_Lich.getLich(malich),HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("admin/lich")
    public ResponseEntity<Object> createLich(@RequestBody DTO_Lich dto, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                String result = (String) ser_Lich.createLich(dto);
                if (result.equals("SUCCESS"))
                    return new ResponseEntity<Object>(result, HttpStatus.OK);
                else return new ResponseEntity<Object>(result, HttpStatus.NOT_ACCEPTABLE);
            } catch (Exception e){
                return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("admin/lich")
    public ResponseEntity<Object> updateLich(@RequestBody DTO_Lich dto_lich, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                String status = (String) ser_Lich.updateLich(dto_lich);
                if (status.equals("SUCCESS"))
                    return new ResponseEntity<Object>(status, HttpStatus.OK);
                else return new ResponseEntity<Object>(status, HttpStatus.NOT_ACCEPTABLE);
            } catch (Exception e){
                return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("admin/lich/{malich}")
    public ResponseEntity<Object> deleteLich(@PathVariable("malich") int malich, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                String status = (String) ser_Lich.deleteLich(malich);
                if (status.equals("SUCCESS"))
                    return new ResponseEntity<Object>(status, HttpStatus.OK);
                else return new ResponseEntity<Object>(status, HttpStatus.NOT_ACCEPTABLE);
            }catch (Exception e){
                return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("admin/lich/idphim={idphim}")
    public ResponseEntity<Object> getLichFollowedByPhim(@RequestHeader("author") String jwt, @PathVariable("idphim") String idphim){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                List<DTO_Lich> result = new ArrayList<>();
                result = (List<DTO_Lich>) ser_Lich.getLichFollowedByPhim(idphim);
                return new ResponseEntity<Object>(result, HttpStatus.OK);
            } catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>("EMPTY", HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("admin/lich/date")
    public ResponseEntity<Object> getLichFollowedByDate(@RequestHeader("author") String jwt, @RequestBody DTO_Lich lich){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                List<DTO_Lich> result = new ArrayList<>();
                result = (List<DTO_Lich>) ser_Lich.getLichFollowedByDate(lich.getNgay());
                return new ResponseEntity<Object>(result, HttpStatus.OK);
            } catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>("EMPTY", HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("admin/lich/current")
    public ResponseEntity<Object> getCurent(@RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                List<DTO_Lich> result = new ArrayList<>();
                result = (List<DTO_Lich>) ser_Lich.getCurrentLich();
                return new ResponseEntity<Object>(result, HttpStatus.OK);
            } catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<Object>(e.toString(),HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }
    @PostMapping("admin/lich/dateandphim")
    public ResponseEntity<Object> getLichFollowedByDateAndPhim(@RequestHeader("author") String jwt, @RequestBody DTO_Lich lich){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                List<DTO_Lich> result = new ArrayList<>();
                result = (List<DTO_Lich>) ser_Lich.getLichFollowedByDateAndPhim(lich.getNgay(), lich.getMaphim());
                return new ResponseEntity<Object>(result, HttpStatus.OK);
            } catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<Object>(e.toString(),HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }
}
