package com.WEBservice_v1.UserController;

import com.WEBservice_v1.DTO.DTO_KhachHang;
import com.WEBservice_v1.Service.Service_AuthorKey;
import com.WEBservice_v1.Service.Service_KhachHang;
import com.WEBservice_v1.Verify;
import com.WEBservice_v1.entity_KeyINFO;
import com.WEBservice_v1.entity_LoginINFO;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Date;

@RestController
public class userUserAPI {
    //Mailer
    @Autowired
    JavaMailSender emailSender;
    @Autowired
    Service_KhachHang ser_khach;
    //Authorization
    @Autowired
    Service_AuthorKey ser_Author;
    static entity_KeyINFO login = new entity_KeyINFO();
    static Verify verifier = new Verify();

    @PostMapping("user/profile")
    public ResponseEntity<Object> getUserINFO(@RequestBody DTO_KhachHang khach){
            try{
                DTO_KhachHang temp = (DTO_KhachHang) ser_khach.getKhachHang(khach.getUsername());
                temp.setPassword("");
                return  new ResponseEntity<>(temp, HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

    }

    @PostMapping("user/create")
    public ResponseEntity<Object> createUser(@RequestBody DTO_KhachHang khachHang){
        try{

            //Check if Mandatory field is Empty or not
            if (!isEmpty(khachHang)){
                //Test data
                System.out.println("USER info: "
                        +"\nUsername: "+khachHang.getUsername()
                        +"\nPassword: "+khachHang.getPassword()
                        +"\nName: "+khachHang.getTen()
                        +"\nNgay sinh: "+khachHang.getNgaysinh()
                        +"\nDia chi: "+khachHang.getDiachi());

                //Not field is empty
                //Check if username is exist or not
                int count = ser_khach.getCountUsername(khachHang.getUsername());
                if (count>0){
                    //Return status code = 208 if Username is already had
                    return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
                } else {
                    khachHang.setNgaygianhap(new Date());
                    khachHang.setRole("user");
                    if (ser_khach.addKhachHang(khachHang).equals("SUCCESS")){
                        SimpleMailMessage message = new SimpleMailMessage();
                        message.setTo(khachHang.getUsername());
                        message.setSubject("Welcome aboard! "+ khachHang.getTen()+"!");
                        //Set Message
                        DecimalFormat formatter = new DecimalFormat("###,###,###");
                        String messageString = "Hi "+khachHang.getHo()+" "+ khachHang.getTen()+":3\n";
                        messageString+="Chào mừng bạn đến với QCINE\n";
                        messageString+="QCINE xin thông báo: bạn đã đăng ký thành viên thành công. Yayyy,\n";
                        messageString+="QCINE cám ơn sự tin tưởng bạn đã dành cho chúng tôi, PEACE OUT\n";
                        message.setText(messageString);
                        emailSender.send(message);
                        return new ResponseEntity<>(HttpStatus.OK);
                    }

                    else
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

            } else {
                //If some field is Emtpy, return status code = 406
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e){
            e.printStackTrace();
            //Return status code = 400 if Request crased
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public static boolean isEmpty(DTO_KhachHang dto){
        if (dto.getTen()==null ||dto.getTen().trim().isEmpty() )
            return true;
        if (dto.getNgaysinh()==null ||dto.getNgaysinh().toString().isEmpty())
            return true;
        if (dto.getUsername()==null ||dto.getUsername().trim().isEmpty())
            return true;
        if (dto.getPassword()==null ||dto.getPassword().trim().isEmpty())
            return true;
        if (dto.getDiachi()==null ||dto.getDiachi().trim().isEmpty())
            return true;
        //Default
        return false;
    }
}
