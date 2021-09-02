package com.WEBservice_v1.UserController;

import com.WEBservice_v1.DTO.*;
import com.WEBservice_v1.Entity.HoaDon;
import com.WEBservice_v1.Service.*;
import com.WEBservice_v1.Verify;
import com.WEBservice_v1.entity_KeyINFO;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class userHoaDonAPI {
    @Autowired
    JavaMailSender emailSender;
    @Autowired
    Service_HoaDon ser_HoaDon;
    @Autowired
    Service_Phim ser_Phim;
    @Autowired
    Service_Lich ser_lich;
    @Autowired
    Service_KhachHang ser_khach;

    @Autowired
    Service_CtHoaDon service_ctHoaDon;
    //Authorization
    @Autowired
    Service_AuthorKey ser_Author;
    static entity_KeyINFO login = new entity_KeyINFO();
    static Verify verifier = new Verify();

    @GetMapping("user/hoadon")
    public Object getHoaDonByUser(@RequestHeader("author") String jwt){
        try{
            if(verifier.verifyUserLogin(jwt, login, ser_Author)){
                String username=Jwts.parser().setSigningKey(login.getKey()).parseClaimsJws(jwt).getBody().get("username", String.class);
                System.out.println("Username: "+ username);
                List<DTO_HoaDon> result = new ArrayList<>();
                result = (List<DTO_HoaDon>) ser_HoaDon.getHhoaDonByUser(username);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else return new ResponseEntity<>("FAIL", HttpStatus.NOT_ACCEPTABLE);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("user/hoadon/{mahd}")
    public Object getHoaDonByMaHD(@PathVariable("mahd") int mahd){
        try{
            DTO_HoaDon result = new DTO_HoaDon();
            result = (DTO_HoaDon) ser_HoaDon.getHD(mahd);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("user/hoadon")
    public Object createHoaDon(@RequestHeader("malich") int malich, @RequestHeader("author") String jwt, @RequestBody List<DTO_CtHoaDon> listSeat){
        if (verifier.verifyUserLogin(jwt, login, ser_Author)){
            String username = Jwts.parser().setSigningKey(login.getKey()).parseClaimsJws(jwt).getBody().get("username", String.class);
            int thanhtien = listSeat.size()*45000;
            Date date = new Date();
            DTO_HoaDon hoadon = new DTO_HoaDon();
            hoadon.setMaKhachHang(username);
            hoadon.setThanhtien(thanhtien);
            hoadon.setMalich(malich);
            hoadon.setTglap(date);
            if (ser_HoaDon.createHD(hoadon).equals("SUCCESS")){
                System.out.println("Hoa don");
                int mahd = ser_HoaDon.getMaHDCreate(malich, username, date, thanhtien);

                System.out.println("Hoa don created: "+mahd);
                boolean isOK = true;
                for( DTO_CtHoaDon temp: listSeat){
                    temp.setGia(45000);
                    temp.setMahd(mahd);
                    if(service_ctHoaDon.createCtHoaDon(temp).equals("FAILED")){
                        isOK = false;
                        System.out.println("CtHoaDon created failed");
                        break;
                    }

                }
                if (!isOK){
                    try{
                        List<DTO_CtHoaDon> delList = (List<DTO_CtHoaDon>) service_ctHoaDon.getCtHoaDonByMaHD(mahd);
                        for (DTO_CtHoaDon delTemp: delList ){
                            service_ctHoaDon.deleteCtHoaDon(delTemp.getMact());
                            System.out.println("CtHoaDon deleted");
                        }
                        ser_HoaDon.deleteHD(mahd);
                        System.out.println("HoaDon deleted");
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    //Get Needed Information
                    DTO_Lich lich = (DTO_Lich) ser_lich.getLich(malich);
                    DTO_Phim phim = (DTO_Phim) ser_Phim.getPhim(lich.getMaphim());
                    DTO_KhachHang khach = (DTO_KhachHang) ser_khach.getKhachHang(username);
                    //Send an Email to User
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setTo(username);
                    message.setSubject("Đặt vé thành công!");
                    //Set Message
                    DecimalFormat formatter = new DecimalFormat("###,###,###");
                    String messageString = "Chào "+ khach.getTen()+"\n";
                    messageString+="Bạn đã đặt vé thành công với các thông tin như sau:\n";
                    messageString+="Tên phim: "+phim.getTenphim()+"\n";
                    messageString+="Suất chiếu vào lúc "+lich.getGio()+" ngày "+lich.getNgay()+"\n";
                    messageString+="Số vé đã đặt: "+ listSeat.size()+"\n";
                    messageString+="Tổng tiền: "+ formatter.format(hoadon.getThanhtien())+" VNĐ"+"\n";
                    messageString+="Bạn có thể xem thông tin chi tiết trên trang Web của chúng tôi\n";
                    messageString+="QCINE cám ơn sự tin tưởng bạn đã dành cho chúng tôi";
                    message.setText(messageString);
                    emailSender.send(message);
                    return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
                }


            } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }

}
