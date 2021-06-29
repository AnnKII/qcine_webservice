package com.WEBservice_v1.AdminController;

import com.WEBservice_v1.Converter.Converter_Lich;
import com.WEBservice_v1.DTO.DTO_Lich;
import com.WEBservice_v1.DTO.DTO_Phim;
import com.WEBservice_v1.DTO.DTO_Phong;
import com.WEBservice_v1.Entity.Phong;
import com.WEBservice_v1.Service.Service_AuthorKey;
import com.WEBservice_v1.Service.Service_Lich;
import com.WEBservice_v1.Service.Service_Phim;
import com.WEBservice_v1.Service.Service_Phong;
import com.WEBservice_v1.Verify;
import com.WEBservice_v1.entity_KeyINFO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@RestController
public class phongAPI {
    @Autowired
    Service_Lich ser_Lich;
    @Autowired
    Service_Phim ser_Phim;
    @Autowired
    Service_Phong ser_Phong;
    //Authorization
    @Autowired
    Service_AuthorKey ser_Author;
    static entity_KeyINFO login = new entity_KeyINFO();
    static Verify verifier = new Verify();

    @GetMapping("admin/phong")
    public ResponseEntity<Object> getAllPhong(@RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                List<DTO_Phong> result = new ArrayList<>();
                result = (List<DTO_Phong>) ser_Phong.getAllPhong();
                return new ResponseEntity<Object>(result, HttpStatus.OK);
            } catch (Exception e){
                return new ResponseEntity<Object>(e.toString(), HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("admin/phong")
    public ResponseEntity<Object> createPhong(@RequestBody DTO_Phong phong, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try {
                String status = (String) ser_Phong.createPhong(phong);
                if (status.equals("SUCCESS"))
                    return new ResponseEntity<Object>(status, HttpStatus.OK);
                else return new ResponseEntity<Object>(status, HttpStatus.NOT_ACCEPTABLE);
            } catch (Exception e){
                return new ResponseEntity<>(e.toString(), HttpStatus.OK);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("admin/phong")
    public ResponseEntity<Object> savePhong(@RequestBody DTO_Phong phong, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try {
                String status = (String) ser_Phong.UpdatePhong(phong);
                if (status.equals("SUCCESS"))
                    return new ResponseEntity<>(status, HttpStatus.OK);
                else return new ResponseEntity<>(status, HttpStatus.NOT_ACCEPTABLE);
            } catch (Exception e){
                return new ResponseEntity<>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("admin/phong/{id}")
    public ResponseEntity<Object> deletePhong(@PathVariable("id") String id, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                String status = (String) ser_Phong.deletePhong(id);
                if(status.equals("SUCCESS"))
                    return new ResponseEntity<>(status, HttpStatus.OK);
                else return new ResponseEntity<>(status, HttpStatus.NOT_ACCEPTABLE);
            }catch (Exception e){
                return new ResponseEntity<>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("admin/phong/{id}")
    public ResponseEntity<Object> getPhong(@PathVariable("id") String idphong, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                DTO_Phong result = (DTO_Phong) ser_Phong.getPhong(idphong);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(e.toString(), HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }
    @PostMapping("admin/phong/getusablephong")
    public ResponseEntity<Object> getUsablePhong(@RequestBody DTO_Lich lich, @RequestHeader("author") String jwt){
        if (verifier.verifyLogin(jwt, login, ser_Author)){
            try{
                DTO_Phim phim = (DTO_Phim) ser_Phim.getPhim(lich.getMaphim());
                if (phim != null){
                    System.out.println("From time: "+ lich.getGio());
                    Time temp = new Time(0);
                    temp.setTime(lich.getGio().getTime() + phim.getThoiluong()*60000);
                    //lich.getGio().setTime(lich.getGio().getTime() + phim.getThoiluong()*60000);
                    System.out.println("TO time: "+ temp);
                    List<DTO_Phong> result = new ArrayList<>();
                    //Get All phong
                    result = (List<DTO_Phong>) ser_Phong.getAllPhong();
                    //Get all lich with date = lich Date
                    try{
                        List<DTO_Lich> currentLich = (List<DTO_Lich>) ser_Lich.getLichFollowedByDate(lich.getNgay());
                        // get list Lich have gio between from and to date
                        for(DTO_Lich tempLich: currentLich){

                            if (((tempLich.getGio().getTime()>= lich.getGio().getTime()) && (tempLich.getGio().getTime()<= temp.getTime()))){
                                DTO_Phong tempPhong = (DTO_Phong) ser_Phong.getPhong(tempLich.getMaphong());
                                int index =-1;
                                for(int j=0; j<result.size(); j++){
                                    if(result.get(j).getIdphong().equals(tempLich.getMaphong()))
                                        index=j;
                                }
                                if(index!=-1)
                                    result.remove(index);
                                System.out.println(tempPhong.getTenphong()+" is Removed");
                            }
                        }
                        System.out.println("----------------------------");
                        System.out.println("List Phong: ");
                        for(DTO_Phong tempPhong: result){
                            System.out.println(tempPhong.getTenphong());
                        }

                        return new ResponseEntity<>(result, HttpStatus.OK);
                    }
                    catch ( Exception e){
                        return new ResponseEntity<>(result, HttpStatus.OK);
                    }
                }
                return new ResponseEntity<>("e.toString()", HttpStatus.NO_CONTENT);


            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(e.toString(), HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>("NO RIGHT", HttpStatus.NOT_ACCEPTABLE);
    }


}
