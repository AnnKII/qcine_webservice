package com.WEBservice_v1;

import com.WEBservice_v1.DTO.DTO_KhachHang;
import com.WEBservice_v1.Service.Service_AuthorKey;
import com.WEBservice_v1.Service.Service_KhachHang;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginAPI {
    @Autowired
    Service_KhachHang ser_Khach;

    @Autowired
    Service_AuthorKey ser_Author;
    entity_KeyINFO login = new entity_KeyINFO();


    @PutMapping("login")
    public Object login(@RequestBody entity_LoginINFO info){
        try{
            System.out.println("Username: "+ info.getUsername());
            System.out.println("Password: "+ info.getPassword());
            LoginController(login);
            DTO_KhachHang dto_Khach = (DTO_KhachHang) ser_Khach.login(info.getUsername().trim(), info.getPassword().trim());
          //  System.out.println("Secrect key: "+ login.getSecrectKey());
            String jwt = Jwts.builder()
                    .claim("username", dto_Khach.getUsername())
                    .claim("name", dto_Khach.getTen())
                    .claim("role", String.valueOf(dto_Khach.getRole()))
                    .signWith(login.getKey()).compact();

            String role = Jwts.parser().setSigningKey(login.getKey()).parseClaimsJws(jwt).getBody().get("role", String.class);
            System.out.println("Role: "+role);
            if (role.trim().equals("admin") || role.trim().equals("nhanvien") )
                return new ResponseEntity<>(jwt, HttpStatus.OK);
            else throw new Exception();
        }
        catch (Exception e){
            System.out.println("LOGIN FAILED");
            //e.printStackTrace();
            return new ResponseEntity<>("FAIL", HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PutMapping("userLogin")
    public Object userLogin(@RequestBody entity_LoginINFO info){
        try{
//            System.out.println("Username: "+ info.getUsername());
//            System.out.println("Password: "+ info.getPassword());
            LoginController(login);
            DTO_KhachHang dto_Khach = (DTO_KhachHang) ser_Khach.login(info.getUsername().trim(), info.getPassword().trim());
            //  System.out.println("Secrect key: "+ login.getSecrectKey());
            String jwt = Jwts.builder()
                    .claim("username", dto_Khach.getUsername())
                    .claim("name", dto_Khach.getTen())
                    .claim("role", String.valueOf(dto_Khach.getRole()))
                    .signWith(login.getKey()).compact();
            String role = Jwts.parser().setSigningKey(login.getKey()).parseClaimsJws(jwt).getBody().get("role", String.class);
            System.out.println("Role: "+role);
            if (role.trim().equals("user"))
                return new ResponseEntity<>(jwt, HttpStatus.OK);
            else throw new Exception();
        }
        catch (Exception e){
            System.out.println("LOGIN FAILED");
            //e.printStackTrace();
            return new ResponseEntity<>("FAIL", HttpStatus.NOT_ACCEPTABLE);
        }
    }
    public  void LoginController(entity_KeyINFO login){
        if (ser_Author.getCount()==1){
            login.setSecrectKey(ser_Author.getSecrectKey());;
            login.setKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(login.getSecrectKey())));
        }
        else {
            ser_Author.deleteAll();
            login.setKey(Keys.secretKeyFor(SignatureAlgorithm.HS256));
            login.setSecrectKey(Encoders.BASE64.encode(login.getKey().getEncoded()));
            if(ser_Author.saveSecrectKey(login.getSecrectKey()))
                System.out.println("Save secrect key SUCCESS");
            else  System.out.println("Save secrect key FAIL");
        }
    }

}
