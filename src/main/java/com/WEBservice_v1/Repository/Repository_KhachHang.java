package com.WEBservice_v1.Repository;

import com.WEBservice_v1.Entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Repository_KhachHang extends JpaRepository<KhachHang, String> {

    @Query(value="select count(username) from KhachHang where username=?1")
    public int getNumberKhach(String username);

    @Query(value=" from KhachHang where username=?1 and pass=?2")
    public KhachHang login(String username, String pass);

    @Query(value="from KhachHang where role=?1")
    public List<KhachHang> getUser(String role);

    @Query(value="from KhachHang where username=?1")
    public KhachHang getKhachHang(String username);
   }
