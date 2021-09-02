package com.WEBservice_v1.AdminController;

import com.WEBservice_v1.DTO.DTO_KhachHang;
import com.WEBservice_v1.Service.Service_AuthorKey;
import com.WEBservice_v1.Service.Service_KhachHang;
import com.WEBservice_v1.Verify;
import com.WEBservice_v1.entity_KeyINFO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class khachhangAPI {

    @Autowired
    Service_KhachHang service_khachHang;
    @Autowired
    Service_AuthorKey ser_Author;
    static entity_KeyINFO login = new entity_KeyINFO();
    static Verify verifier = new Verify();


    @GetMapping("admin/khachhang")
    public  ResponseEntity<Object> getAllKhachHang(@RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            List<DTO_KhachHang> result = new ArrayList<>();
            try{
                result = (List<DTO_KhachHang>) service_khachHang.getAllKhachHang();
                return  new ResponseEntity<Object>(result, HttpStatus.OK);
            }
            catch (Exception e){
                return new ResponseEntity<Object>("EMPTY", HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("admin/user/{role}")
    public ResponseEntity<Object>getUserWithSpecificrole(@RequestHeader("author") String jwt, @PathVariable("role") String role){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            List<DTO_KhachHang> result = new ArrayList<>();
            try{
                result = (List<DTO_KhachHang>) service_khachHang.getUserWithSpecificRole(role);
                return  new ResponseEntity<Object>(result, HttpStatus.OK);
            }
            catch (Exception e){
                return new ResponseEntity<Object>("EMPTY", HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("admin/khachhang/{id}")
    public ResponseEntity<Object> getKhacHang(@PathVariable("id") String username, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try {
                DTO_KhachHang result = (DTO_KhachHang) service_khachHang.getKhachHang(username);
                return new ResponseEntity<Object>(result, HttpStatus.OK);
            }
            catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<Object>("NOT FOUND", HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }
    @PostMapping("admin/khachhang")
    public ResponseEntity<Object> createKhachHang (@RequestBody DTO_KhachHang dto, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                String result = (String) service_khachHang.addKhachHang(dto);
                if(result.equals("SUCCESS"))
                    return new ResponseEntity<Object>(result,HttpStatus.OK);
                else return new ResponseEntity<Object>(result, HttpStatus.NOT_ACCEPTABLE);
            } catch (Exception e){
                return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("admin/khachhang")
    public ResponseEntity<Object> updateKhacHang(@RequestBody DTO_KhachHang dto, @RequestHeader("author") String jwt){
       if (verifier.verifyLogin(jwt, login, ser_Author)){
           try{
               String result = (String) service_khachHang.updateKhachHang(dto);
               if (result.equals("SUCCESS"))
                   return new ResponseEntity<Object>(result,HttpStatus.OK);
               else return new ResponseEntity<Object>(result, HttpStatus.NOT_ACCEPTABLE);
           } catch (Exception e){
               return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
           }
       } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("admin/khachhang")
    public  ResponseEntity<Object> deleteKhachHang(@RequestHeader("id") String username, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                String result = (String) service_khachHang.deleteKhachHang(username);
                if (result.equals("SUCCESS"))
                    return new ResponseEntity<Object>(result, HttpStatus.OK);
                else return new ResponseEntity<Object>(result, HttpStatus.NOT_ACCEPTABLE);
            } catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("admin/khachhang/check/{username}")
    public ResponseEntity<Object> getNumberKhach(@PathVariable("username") String username, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                int result = service_khachHang.getCountUsername(username);
                return  new ResponseEntity<>(result, HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }
}
