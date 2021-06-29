package com.WEBservice_v1.AdminController;

import com.WEBservice_v1.DTO.DTO_HoaDon;
import com.WEBservice_v1.Service.Service_AuthorKey;
import com.WEBservice_v1.Service.Service_HoaDon;
import com.WEBservice_v1.Verify;
import com.WEBservice_v1.entity_KeyINFO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class hoadonAPI {
    @Autowired
    Service_HoaDon ser_HD;
    //Authorization
    @Autowired
    Service_AuthorKey ser_Author;
    static entity_KeyINFO login = new entity_KeyINFO();
    static Verify verifier = new Verify();

    @GetMapping("admin/hoadon")
    public ResponseEntity<Object> getAllHoaDon(@RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                List<DTO_HoaDon> result = (List<DTO_HoaDon>) ser_HD.getAllHD();
                return new ResponseEntity<Object>(result, HttpStatus.OK);
            }
            catch (Exception e){
                return new ResponseEntity<Object>("EMPTY", HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("admin/hoadon/{mahd}")
    public ResponseEntity<Object> getHoaDon(@PathVariable("mahd") int mahd, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                DTO_HoaDon result = (DTO_HoaDon) ser_HD.getHD(mahd);
                return new ResponseEntity<Object>(result, HttpStatus.OK);
            }
            catch (Exception e){
                return new ResponseEntity<Object>("NOT FOUND", HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("admin/hoadon/khachhang/{username}")
    public ResponseEntity<Object> getHoaDonFollowedByKhachHang(@RequestHeader("author") String jwt,@PathVariable("username") String username){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                List<DTO_HoaDon> result = (List<DTO_HoaDon>) ser_HD.getHhoaDonByUser(username);
                return new ResponseEntity<Object>(result, HttpStatus.OK);
            }
            catch (Exception e){
                return new ResponseEntity<Object>("EMPTY", HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }
    @PostMapping("admin/hoadon")
    public ResponseEntity<Object> createHoaDon(@RequestBody DTO_HoaDon dto, @RequestHeader("author") String jwt){
       if (verifier.verifyLogin(jwt, login, ser_Author)){
           String status = (String) ser_HD.createHD(dto);
           if (status.equals("SUCCESS")){
                int mahd = ser_HD.getMaHDCreate(dto.getMalich(), dto.getMaKhachHang(), dto.getTglap(), dto.getThanhtien());
               if(mahd!=-1){
                   System.out.println("Ma hoa don: "+mahd);
                   return new ResponseEntity<Object>(mahd, HttpStatus.OK);
               }

               else return new ResponseEntity<Object>("failed", HttpStatus.NOT_ACCEPTABLE);
           }

           else return new ResponseEntity<Object>(status, HttpStatus.NOT_ACCEPTABLE);
       } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("admin/hoadon")
    public ResponseEntity<Object> updateHoaDon(@RequestBody DTO_HoaDon dto, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            String status = (String) ser_HD.updateHD(dto);
            if (status.equals("SUCCESS"))
                return new ResponseEntity<Object>("SUCCESS", HttpStatus.OK);
            else return new ResponseEntity<Object>(status, HttpStatus.NOT_ACCEPTABLE);
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("admin/hoadon/{mahd}")
    public ResponseEntity<Object> deleteHoaDon(@PathVariable("mahd") int mahd, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            String status = (String) ser_HD.deleteHD(mahd);
            if (status.equals("SUCCESS"))
                return new ResponseEntity<Object>("SUCCESS", HttpStatus.OK);
            else return new ResponseEntity<Object>(status, HttpStatus.NOT_ACCEPTABLE);
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

}
